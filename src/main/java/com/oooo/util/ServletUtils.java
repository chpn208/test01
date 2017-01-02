package com.oooo.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Http与Servlet工具
 * @date 2016年4月6日
 */
public class ServletUtils {

	//-- Content Type 定义 --//
	public static final String TEXT_TYPE = "text/plain";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";
	
	//-- header 常量定义 --//
	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final boolean DEFAULT_NOCACHE = true;

	private static ObjectMapper mapper = new ObjectMapper();


	//-- Header 定义 --//
	public static final String AUTHENTICATION_HEADER = "Authorization";

	//-- 常用数�?定义 --//
	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

	
	//-- 取得Request/Response/Session的简化函�?--//
	
	/**
	 * 取得HttpSession的简化函�?
	 */
	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}

	/**
	 * 取得HttpSession的简化函�?
	 */
	public static HttpSession getSession(HttpServletRequest request,boolean isNew) {
		return request.getSession(isNew);
	}

	/**
	 * 取得HttpSession中Attribute的简化函�?
	 */
	public static Object getSessionAttribute(HttpServletRequest request,String name) {
		HttpSession session = getSession(request,false);
		return (session != null ? session.getAttribute(name) : null);
	}

	//-- 绕过jsp/freemaker直接输出文本的函�?--//
	/**
	 * 直接输出内容的简便函�?

	 * eg.
	 * render("text/plain", "hello", "encoding:GBK");
	 * render("text/plain", "hello", "no-cache:false");
	 * render("text/plain", "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param headers 可变的header数组，目前接受的值为"encoding:"�?no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render(HttpServletResponse response,final String contentType, final String content, final String... headers) {
		response = initResponseHeader(response,contentType, headers);
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出文本.
	 * @see #render(String, String, String...)
	 */
	public static void renderText(HttpServletResponse response,final String text, final String... headers) {
		render(response,ServletUtils.TEXT_TYPE, text, headers);
	}

	/**
	 * 直接输出HTML.
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(HttpServletResponse response,final String html, final String... headers) {
		render(response,ServletUtils.HTML_TYPE, html, headers);
	}

	/**
	 * 直接输出XML.
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(HttpServletResponse response,final String xml, final String... headers) {
		render(response,ServletUtils.XML_TYPE, xml, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param jsonString json字符�?
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(HttpServletResponse response,final String jsonString, final String... headers) {
		render(response,ServletUtils.JSON_TYPE, jsonString, headers);
	}

	/**
	 * 直接输出JSON,使用Jackson转换Java对象.
	 * 
	 * @param data 可以是List<POJO>, POJO[], POJO, 也可以Map名�?�?
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(HttpServletResponse response,final Object data, final String... headers) {
		response = initResponseHeader(response,ServletUtils.JSON_TYPE, headers);
		try {
			mapper.writeValue(response.getWriter(), data);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 直接输出支持跨域Mashup的JSONP.
	 * 
	 * @param callbackName callback函数�?
	 * @param String 字符串文�?
	 */
	public static void renderJsonp(HttpServletResponse response,final String callbackName, final String text, final String... headers) {
		String outText = "";
		if(text != null){
			outText = text;
		}
		String result = new StringBuilder().append(callbackName).append("(").append(outText).append(");").toString();

		//渲染Content-Type为javascript的返回内�?输出结果为javascript语句, 如callback197("{html:'Hello World!!!'}");
		render(response,ServletUtils.JS_TYPE, result, headers);
	}
	
	
	/**
	 * 直接输出支持跨域Mashup的JSONP.
	 * 
	 * @param callbackName callback函数�?
	 * @param object Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名�?�? 将被转化为json字符�?
	 */
	public static void renderJsonp(HttpServletResponse response,final String callbackName, final Object object, final String... headers) {
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}

		String result = new StringBuilder().append(callbackName).append("(").append(jsonString).append(");").toString();

		//渲染Content-Type为javascript的返回内�?输出结果为javascript语句, 如callback197("{html:'Hello World!!!'}");
		render(response,ServletUtils.JS_TYPE, result, headers);
	}

	/**
	 * 分析并设置contentType与headers.
	 */
	private static HttpServletResponse initResponseHeader(HttpServletResponse response,final String contentType, final String... headers) {
		//分析headers参数
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for (String header : headers) {
			String headerName = StringUtils.substringBefore(header, ":");
			String headerValue = StringUtils.substringAfter(header, ":");

			if (StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else {
				throw new IllegalArgumentException(headerName + "不是�?��合法的header类型");
			}
		}

		//设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			ServletUtils.setDisableCacheHeader(response);
		}

		return response;
	}

	
	/**
	 * 设置客户端缓存过期时�?的Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		//Http 1.0 header
		response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000);
		//Http 1.1 header
		response.setHeader("Cache-Control", "private, max-age=" + expiresSeconds);
	}

	/**
	 * 设置禁止客户端缓存的Header.
	 */
	public static void setDisableCacheHeader(HttpServletResponse response) {
		//Http 1.0 header
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");
		//Http 1.1 header
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
	}

	/**
	 * 设置LastModified Header.
	 */
	public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	/**
	 * 设置Etag Header.
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	/**
	 * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
	 * 
	 * 如果无修�? checkIfModify返回false ,设置304 not modify status.
	 * 
	 * @param lastModified 内容的最后修改时�?
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response,
			long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 根据浏览�?If-None-Match Header, 计算Etag是否已无�?
	 * 
	 * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
	 * 
	 * @param etag 内容的ETag.
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
		String headerValue = request.getHeader("If-None-Match");
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!"*".equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}
			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader("ETag", etag);
				return false;
			}
		}
		return true;
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 
	 * @param fileName 下载后的文件
	 */
	public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
		try {
			//中文文件名
			String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
		} catch (UnsupportedEncodingException e) {
		}
	}

	/**
	 * 
	 * 
	 * 返回的结果的Parameter名已去除前缀.
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
		Assert.notNull(request, "Request must not be null");
		Enumeration paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}

	
	
	/**
	 * 获取完整请求的参数
	 * @param request
	 * @return params  
	 */
	public static Map<String,Object> getRequestParameters(HttpServletRequest req) {
		Map<String,Object> params = new HashMap<String, Object>();
		Iterator<Entry<String, String[]>> iterator = req.getParameterMap().entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String[]> entry = iterator.next();
			for (int i = 0; i < entry.getValue().length; i++) {
				params.put(entry.getKey(), entry.getValue()[0]);
			}
		}
		return params;
	}
	
	
	/**
	 * 获取完整请求的参�?
	 * @param request
	 * @return
	 */
	public static Map<String,Object> getRequestParameters(String param,String splitChar) {
		Map<String,Object> params = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(param)) {
				throw new Exception("参数输入不合法，param不能为空");
			}
			String[] sparam = param.split(splitChar);
			for (String p : sparam) {
				String[] kv = p.split("=");
				params.put(kv[0], kv[1]);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return params;
	}
	
	/**
	 * 获取完整请求的参数，
	 * @param request
	 * @return
	 */
	public static Map<String,Object> getRequestParameters(String param) {
		Map<String,Object> params = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(param)) {
				throw new Exception("参数输入不合法，param不能为空");
			}
			String[] sparam = param.split("&");
			for (String p : sparam) {
				String[] kv = p.split("=");
				params.put(kv[0], kv[1]);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return params;
	}
}

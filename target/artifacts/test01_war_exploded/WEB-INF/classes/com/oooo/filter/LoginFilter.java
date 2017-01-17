package com.oooo.filter;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public class LoginFilter implements Filter {
    @Override
    public void destroy() {

    }

    String passUrl = "";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        List<String> strArray = Lists.newArrayList();
        String requestUri = ((HttpServletRequest) request).getRequestURI();
        if (requestUri.endsWith(".js") || requestUri.endsWith(".css") ||requestUri.endsWith(".ico")){

            return;
        }
        if (StringUtils.isNotEmpty(passUrl)){
            String[] passUrls = passUrl.split(";");
            strArray = Arrays.asList(passUrls);

        }
        for (String str : strArray) {
            if (str.equals(""))
                continue;
            if (httpRequest.getRequestURL().indexOf(str) >= 0) {
                chain.doFilter(request, response);
                return;
            }
        }

        HttpSession session = httpRequest.getSession();
        if (session.getAttribute("username") != null) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        passUrl = arg0.getInitParameter("passUrl");
    }

}

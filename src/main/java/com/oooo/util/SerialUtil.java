package com.oooo.util;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * JDBC 类
 * 
 * @author Administrator
 *
 */

public class SerialUtil {

	private static JdbcTemplate jdbcTemplate;



	public  JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public  void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		SerialUtil.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 查询Sql总记录数
	 * 
	 * @param queryString
	 * @return
	 */
	public Integer getCount(String queryString) {
		List<Map<String, Object>> str = jdbcTemplate.queryForList(queryString);
		return str.size();
	}
	
	/**
	 * 查询搜索分页语句（去重复的send_id）
	 * @param queryString
	 * @return
	 */
	public Integer getCountByGroup(String queryString) {
		List<Map<String, Object>> str = jdbcTemplate.queryForList(queryString);
		if (str.size()>0) {
			String cnt = String.valueOf(str.get(0).get("cnt"));
			if(cnt!=null && Integer.valueOf(cnt)>0){
				return str.size();
			}
		}
		return 0;
	}

	/**
	 * 查询Sql总记录数
	 * 
	 * @param queryString
	 * @return
	 */
	public Integer getTotalCount(String queryString) {

		List<Map<String, Object>> str = jdbcTemplate.queryForList(queryString);
		Map<String, Object> map = (Map<String, Object>) str.get(0);
		Integer count = ((Long) map.get("count(1)")).intValue();
		return count;
	}

	/**
	 * 
	 * @param queryString
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List getStr(String queryString) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString);
		return list;
	}

	/**
	 * 查询记录的条数 sql 包含count(*)
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Integer queryForInt(String queryString) {
		int qty = jdbcTemplate.queryForInt(queryString);
		return qty;
	}

	/**
	 * 根据sql语句返回相对应map的list
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> getBySQL(String sql) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	/**
	 * 
	 * @param sql
	 * @return
	 */
	public static int getUpdate(String sql) {
		int count = jdbcTemplate.update(sql);
		return count;
	}

	/**
	 * 查询返回分页数据
	 * 
	 * @param SQL
	 * @param 当前页
	 * @param 每页显示数
	 * @return Map totalpage总页数，page当前页，list明细
	 */
	public Map<String, Object> getPageBySQL(String queryString, Integer page,
			Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer count = this.getCount(queryString);
		Integer totalpage = 0;
		Integer startIndex = 0;
		if (count == 0) {
			return null;
		} else {
			if (count / pageSize < 1) {
				totalpage = 1;
			} else if (count % pageSize == 0) {
				totalpage = count / pageSize;
			} else {
				totalpage = 1 + count / pageSize;
			}
			if (totalpage < page) {
				page = totalpage;
			}
			if (totalpage < page && totalpage <= 1) {
				page = 1;
			}
			if (page <= 0) {
				page = 1;
				startIndex = 0;
			} else {
				startIndex = (page - 1) * pageSize;
			}

		}
		String sql = getMySQLPageSQL(queryString, startIndex, pageSize);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		map.put("query", queryString);
		map.put("page", page);
		map.put("totalpage", totalpage);
		map.put("list", list);
		map.put("count", count);
		return map;
	}

	/**
	 * 查询返回分页数据
	 * 
	 * @param SQL
	 * @param 当前页
	 * @param 每页显示数
	 * @return Map totalpage总页数，page当前页，list明细
	 */
	public List<Map<String, Object>> getPageBySQL(String queryString,
			String countString, Integer page, Integer pageSize,Object[] parmObject) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Integer startIndex =0;	
	    if (page <= 0) {
				page = 1;
				startIndex = 0;
		} else {
				startIndex = (page - 1) * pageSize;
		}
		
		String sql = getMySQLPageSQL(queryString, startIndex, pageSize);
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,parmObject);
		
		
		
		return list;
	}

	private String getMySQLPageSQL(String queryString, Integer startIndex,
			Integer pageSize) {
		String result = "";
		if (null != startIndex && null != pageSize) {
			result = queryString + " limit " + startIndex + "," + pageSize;
		} else if (null != startIndex && null == pageSize) {
			result = queryString + " limit " + startIndex;
		} else {
			result = queryString;
		}
		return result;
	}




}
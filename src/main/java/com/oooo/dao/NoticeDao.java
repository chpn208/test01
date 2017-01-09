package com.oooo.dao;

import com.oooo.model.Notice;

import java.util.Map;

/**
 * Created by chenpan on 17-1-8.
 */
public interface NoticeDao {
    public int update(Notice notice);
    public Notice findByType(Map<String,Integer> parameterMap);
}

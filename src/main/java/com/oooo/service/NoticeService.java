package com.oooo.service;

import com.google.common.collect.Maps;
import com.oooo.dao.NoticeDao;
import com.oooo.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by chenpan on 17-1-8.
 */
@Service
public class NoticeService {
    @Autowired
    NoticeDao noticeDao;
    public Notice findByType(int type){
        Map<String,Integer> parameterMap = Maps.newHashMap();
        parameterMap.put("type",type);
        return noticeDao.findByType(parameterMap);
    }

    public int update(Notice notice){
        return noticeDao.update(notice);
    }
}

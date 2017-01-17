package com.oooo.service;

import com.google.common.collect.Maps;
import com.mysql.jdbc.StringUtils;
import com.oooo.dao.UserDao;
import com.oooo.model.User;
import com.oooo.util.Constant;
import com.oooo.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/27.
 */
@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDao dao;

    public User findById(int id){
        logger.debug("findById.........");
        return dao.getById(id);
    }
    public User findByName(String name){
        User user = dao.getByName(name);
        return user;
    }

    public Integer findUserIdByKeyCode(String _keyCode){
        /*Map<String,String> parameterMap = Maps.newHashMap();
        parameterMap.put("keyCode",keyCode);*/
//        return dao.getByKeyCode(_keyCode);
        String sql = MessageFormat.format("select * from user u where u.key_code=''{0}''", _keyCode);
        List<Map<String, Object>> resultMapList = Constant.getInstance().getSerialUtil().getBySQL(sql);
        if(resultMapList != null || resultMapList.size() > 0){
            Map<String, Object> resultMap = resultMapList.get(0);
            Integer userId = (Integer) resultMap.get("id");
            return userId;
        }
        return 0;

    }
    public List<User> findMembers(Map<String,Integer> parameterMap){
        return dao.getMembers(parameterMap);
    }

    public User addUser(User user){
        String key = MD5.md5(user.getName());
        user.setKeyCode(key);
        dao.add(user);
        return user;
    }

    public List<User> getByPage(User user,int pageSize,int pageNum){
        int startNum = pageNum * pageSize;
        int endNum = (pageNum+1) * pageSize;
        Map<String,Integer> parameterMap = Maps.newHashMap();
        parameterMap.put("id",user.getId());
        parameterMap.put("userlevel",user.getLevel());
        parameterMap.put("startNum",startNum);
        parameterMap.put("endNum",endNum);
        List<User> users = dao.getPageMembers(parameterMap);
        return users;
    }

    public int updateUser(User user){
        return dao.update(user);
    }

    public long getCount(Map<String,Integer> parameterMap){
        long count = dao.getCount(parameterMap);
        return count;
    }
}

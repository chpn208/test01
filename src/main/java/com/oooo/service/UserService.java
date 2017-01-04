package com.oooo.service;

import com.google.inject.internal.Maps;
import com.oooo.dao.UserDao;
import com.oooo.model.User;
import com.oooo.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/27.
 */
@Service
public class UserService {
    @Autowired
    private UserDao dao;

    public User findById(int id){
        return dao.getById(id);
    }
    public User findByName(String name){
        User user = dao.getByName(name);
        return user;
    }

    public List<User> findMembers(){
        return dao.getMembers();
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

    public long getCount(User user){
        Map<String,Integer> parameterMap = Maps.newHashMap();
        parameterMap.put("userId",user.getId());
        parameterMap.put("userlevel",user.getLevel());
        long count = dao.getCount(parameterMap);
        return count;
    }
}

package com.oooo.dao;

import com.oooo.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/18.
 */
public interface UserDao {
    User getById(int userId);
    int update(User user);
    User getByName(String name);
    List<User> getMembers(Map<String,Integer> parameterMap);
    int add(User user);
    long getCount(Map<String,Integer> parameterMap);
    List<User> getPageMembers(Map<String,Integer> parameterMap);
    User getByKeyCode(String keyCode);
}

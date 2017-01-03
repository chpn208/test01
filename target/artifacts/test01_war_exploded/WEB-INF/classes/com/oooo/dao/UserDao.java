package com.oooo.dao;

import com.oooo.model.User;

import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 */
public interface UserDao {
    User getById(int id);
    void update(User user);
    User getByName(String name);
    List<User> getMembers();
    int add(User user);
}

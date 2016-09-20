package com.oooo.dao;

import com.oooo.model.User;

/**
 * Created by Administrator on 2016/9/18.
 */
public interface UserDao {
    User getById(int id);
    void update(User user);
}

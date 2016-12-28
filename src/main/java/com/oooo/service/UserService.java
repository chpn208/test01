package com.oooo.service;

import com.oooo.dao.UserDao;
import com.oooo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

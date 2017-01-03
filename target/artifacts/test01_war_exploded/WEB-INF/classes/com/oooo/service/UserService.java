package com.oooo.service;

import com.oooo.dao.UserDao;
import com.oooo.model.User;
import com.oooo.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

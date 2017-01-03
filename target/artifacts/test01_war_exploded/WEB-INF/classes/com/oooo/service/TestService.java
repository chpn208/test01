package com.oooo.service;

import com.oooo.dao.UserDao;
import com.oooo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/9/18.
 */
@Service
public class TestService {
    @Autowired
    private UserDao dao;
    public void testService(){
        User user = dao.getById(1);
        System.out.println(user.getName());
        System.out.println("aaaa");
    }
}

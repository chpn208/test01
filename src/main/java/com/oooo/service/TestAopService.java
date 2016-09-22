package com.oooo.service;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/9/19.
 */
@Service
public class TestAopService {
    private int id;
    public void service(){
        System.out.println("service "+id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package com.oooo.service;

/**
 * Created by Administrator on 2016/9/19.
 */
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

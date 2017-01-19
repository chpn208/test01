package com.oooo.dao;

import com.oooo.model.BroadCast;

import java.util.List;

/**
 * Created by chenpan on 17-1-19.
 */
public interface BroadCastDao {
    BroadCast findById(int id);
    List<BroadCast> findAll();
    int add(BroadCast broadCast);
    int update(BroadCast broadCast);
}

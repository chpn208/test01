package com.oooo.service;

import com.oooo.dao.BroadCastDao;
import com.oooo.model.BroadCast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenpan on 17-1-19.
 */
@Service
public class BroadCastService {
    @Autowired
    BroadCastDao broadCastDao;

    public void add(BroadCast broadCast){
        broadCastDao.add(broadCast);
    }

    public void update(BroadCast broadCast){
        broadCastDao.update(broadCast);
    }

    public List<BroadCast> findAll(){
        return broadCastDao.findAll();
    }
    public BroadCast findById(int id){
        return broadCastDao.findById(id);
    }
}

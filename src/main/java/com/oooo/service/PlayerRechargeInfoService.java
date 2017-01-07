package com.oooo.service;

import com.oooo.dao.PlayerRechargeInfoDao;
import com.oooo.model.PlayerRechargeInfo;
import com.oooo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by chenpan on 17-1-4.
 */
@Service
public class PlayerRechargeInfoService {
    @Autowired
    PlayerRechargeInfoDao playerRechargeInfoDao;

    public List<PlayerRechargeInfo> getByPage( Map<String,Integer> parameterMap){
        return playerRechargeInfoDao.getByPage(parameterMap);
    }

    public int add(PlayerRechargeInfo info){
        return playerRechargeInfoDao.add(info);
    }

    public List<PlayerRechargeInfo> getAll(){
        return playerRechargeInfoDao.getAll();
    }

    public int getCount(Map<String ,Integer> parameterMap){
        return playerRechargeInfoDao.getCount(parameterMap);
    }
}

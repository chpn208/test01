package com.oooo.dao;

import com.oooo.model.PlayerRechargeInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by chenpan on 17-1-4.
 */
public interface PlayerRechargeInfoDao {
     int add(PlayerRechargeInfo playerRechargeInfo);
     PlayerRechargeInfo getById(int id);
     List<PlayerRechargeInfo> getByPage(Map<String,Integer> parameterMap);
     int getCount(Map<String,Integer> parameterMap);
     List<PlayerRechargeInfo> getAll();
}

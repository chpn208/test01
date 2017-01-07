package com.oooo.dao;

import com.oooo.model.AgentRechargeInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by chenpan on 17-1-4.
 */
public interface AgentRechargeInfoDao {
    public List<AgentRechargeInfo> getByPage(Map<String,Integer> parameterMap);

    public int count(Map<String,Integer> parameterMap);

    public int add(AgentRechargeInfo agentRechargeInfo);
}

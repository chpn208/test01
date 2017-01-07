package com.oooo.service;

import com.oooo.dao.AgentRechargeInfoDao;
import com.oooo.model.AgentRechargeInfo;
import com.oooo.model.RechargeSend;
import com.oooo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by chenpan on 17-1-4.
 */
@Service
public class AgentService {

    @Autowired
    AgentRechargeInfoDao agentRechargeInfoDao;

    public List<AgentRechargeInfo> getPageAgentRechargeInfo(Map<String,Integer> parameterMap){
        List<AgentRechargeInfo> result = agentRechargeInfoDao.getByPage(parameterMap);
        return result;
    }

    public int add(AgentRechargeInfo agentRechargeInfo){
        return agentRechargeInfoDao.add(agentRechargeInfo);
    }
    public int count(Map<String,Integer> parameterMap){
        return agentRechargeInfoDao.count(parameterMap);
    }

    //recharge for agent
    public AgentRechargeInfo rechargeForAgent(User user, User toAgent, int rechargeNum,int sendNum){
        AgentRechargeInfo agentRechargeInfo = new AgentRechargeInfo();
        agentRechargeInfo.setRechargeNum(rechargeNum);
        agentRechargeInfo.setSendNum(sendNum);
        agentRechargeInfo.setRechargeId(user.getId());
        agentRechargeInfo.setUserId(toAgent.getId());
        agentRechargeInfo.setTime(new Date());
        agentRechargeInfoDao.add(agentRechargeInfo);
        return agentRechargeInfo;
    }
}

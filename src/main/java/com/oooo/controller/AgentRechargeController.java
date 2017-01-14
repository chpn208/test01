package com.oooo.controller;

import com.google.common.collect.Maps;
import com.oooo.model.AgentRechargeInfo;
import com.oooo.model.RechargeSend;
import com.oooo.model.User;
import com.oooo.service.AgentService;
import com.oooo.service.UserService;
import com.oooo.util.Constant;
import com.oooo.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by chenpan on 17-1-4.
 */
@Controller
@RequestMapping("/agent")
public class AgentRechargeController {
    private Logger logger = LoggerFactory.getLogger(AgentRechargeController.class);
    @Autowired
    UserService userService;
    @Autowired
    AgentService agentService;

    //我的充值
    @RequestMapping("/my/recharge")
    public String getMyRecharge(HttpServletRequest request, Model model,
                                @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute(Constant.getInstance().USER_ID);
        User user = userService.findById(userId);

        int startNum = pageNum * pageSize;
        int endNum = (pageNum+1)*pageSize;

        Map<String,Integer> parameterMap = Maps.newHashMap();
        parameterMap.put("userId",user.getId());
        parameterMap.put("startNum", startNum);
        parameterMap.put("endNum",endNum);
        List<AgentRechargeInfo> list = agentService.getPageAgentRechargeInfo(parameterMap);
        int count= agentService.count(parameterMap);
        int pageCount = count % pageSize ==0 ? count/pageSize : count/pageSize+1;
        Page<AgentRechargeInfo> page = new Page<>();
        page.setStartNum(startNum);
        page.setEndNum(endNum);
        page.setResult(list);
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setPageCount(pageCount);
        page.setCount(count);
        model.addAttribute("page",page);
        return "/agent/rechargeList";
    }

    @RequestMapping("/preRecharge")
    public String preAgentRecharge(HttpServletRequest request,Model model,
                                   @RequestParam(value = "agentId",required = true) Integer agentId){

        Integer userId = (Integer) request.getSession().getAttribute(Constant.getInstance().USER_ID);
        if (userId == null){
            return "";
        }
        User user = userService.findById(userId);

        User agent = userService.findById(agentId);
        if (user.getLevel() < 10){
           if (agent.getParentUser() != userId){
               return "";
           }
        }

        RechargeSend rechargeSend = Constant.getInstance().getRechargeSendMap().get(user.getLevel());
        model.addAttribute("chargeNum",rechargeSend.getRechargeNum());
        model.addAttribute("sendNum",rechargeSend.getReturnNum());
        model.addAttribute("countDiamond",user.getDiamond());

        if (agent == null){
            return "";
        }

        model.addAttribute("agent",agent);
        return "/agent/recharge";
    }
    @RequestMapping("/my/buyed")
    public String myByedRecharges(HttpServletRequest request,Model model,
                                  @RequestParam(value = "pageNum",defaultValue = "0") int pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(Constant.getInstance().USER_ID);
        User user = userService.findById(userId);

        int startNum = pageNum * pageSize;
        int endNum = (pageNum+1)*pageSize;

        Map<String,Integer> parameterMap = Maps.newHashMap();
        parameterMap.put("rechargeId",user.getId());
        parameterMap.put("startNum", startNum);
        parameterMap.put("endNum",endNum);
        List<AgentRechargeInfo> list = agentService.getPageAgentRechargeInfo(parameterMap);
        int count= agentService.count(parameterMap);
        int pageCount = count % pageSize ==0 ? count/pageSize : count/pageSize+1;

        Page<AgentRechargeInfo> page = new Page<>();
        page.setStartNum(startNum);
        page.setEndNum(endNum);
        page.setResult(list);
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setPageCount(pageCount);
        page.setCount(count);
        model.addAttribute("page",page);

        return "/agent/mybuyedList";
    }

    @RequestMapping("/myAgentList")
    public String mytAgentList(HttpServletRequest request,Model model,
                               @RequestParam(value = "pageNum",defaultValue = "0") Integer pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(Constant.getInstance().USER_ID);

        Map<String,Integer> parameterMap = Maps.newHashMap();
        parameterMap.put("parentUser",userId);
        int startNum = pageNum * pageSize;
        int endNum = (pageNum + 1) * pageSize;
        parameterMap.put("startNum",startNum);
        parameterMap.put("endNum",endNum);
        List<User> result = userService.findMembers(parameterMap);
        int count = (int) userService.getCount(parameterMap);
        int pageCount = count % pageSize == 0? count/pageSize : count/pageSize + 1;
        Page<User> page = new Page<>();
        page.setStartNum(startNum);
        page.setEndNum(endNum);
        page.setResult(result);
        page.setCount(count);
        page.setPageCount(pageCount);
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        model.addAttribute("page",page);

        return "/agent/myAgentList";
    }


    @RequestMapping("/agentRecharge")
    public String recharge(HttpServletRequest request,Model model,
                           @RequestParam(value = "agentId",required = true) int toUserId,
                           @RequestParam(value = "rechargedNum",required = true) int rechargedNum){
        HttpSession session = request.getSession();
        //String userName = (String) session.getAttribute("userName");
        Integer userId = (Integer) session.getAttribute(Constant.getInstance().USER_ID);
        User user = userService.findById(userId);
        User toUser = userService.findById(toUserId);
        if (toUser.getParentUser() != userId){
            return "201";
        }
        if (user.getLevel() < 10){
            if (toUser.getParentUser() != userId){
               return "202" ;
            }
        }
        RechargeSend rechargeSend = Constant.getInstance().getRechargeSendMap().get(user.getLevel());
        int sendCount = rechargedNum /rechargeSend.getRechargeNum();
        synchronized (this){
            if (user.getLevel() <= 10) {
                if (user.getDiamond() < rechargedNum){
                    return "203";
                }
                int diamond = user.getDiamond() - rechargedNum;
                user.setDiamond(diamond);
                userService.updateUser(user);
            }
            int countDiamond = toUser.getDiamond()+rechargedNum + sendCount;
            toUser.setDiamond(countDiamond);
            userService.updateUser(toUser);
            AgentRechargeInfo agentRechargeInfo = new AgentRechargeInfo();
            agentRechargeInfo.setRechargeNum(rechargedNum);
            agentRechargeInfo.setSendNum(sendCount);
            agentRechargeInfo.setRechargeId(toUserId);
            agentRechargeInfo.setUserId(userId);
            agentRechargeInfo.setTime(new Date());
            agentService.add(agentRechargeInfo);
        }

        return "/agent/rechargeList";
    }

    @RequestMapping("/recommend")
    public String toRecommend(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(Constant.getInstance().USER_ID);
        User user = userService.findById(userId);
        model.addAttribute("code",user.getKeyCode());
        return "/agent/recommend";
    }
}

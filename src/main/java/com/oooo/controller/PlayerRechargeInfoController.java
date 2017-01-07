package com.oooo.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.oooo.model.PlayerRechargeInfo;
import com.oooo.model.RechargeSend;
import com.oooo.model.User;
import com.oooo.service.PlayerRechargeInfoService;
import com.oooo.service.UserService;
import com.oooo.util.Constant;
import com.oooo.util.Page;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/agent/playerRecharge")
public class PlayerRechargeInfoController {
    @Autowired
    PlayerRechargeInfoService playerRechargeInfoService;
    @Autowired
    UserService userService;

    @RequestMapping("/playerInfo")
    public String getPlayerInfo(HttpServletRequest request,Model model){
        String playerIdStr = request.getParameter("playerId");
        if (StringUtils.isNumeric(playerIdStr)){
            Integer playerId = Integer.parseInt(playerIdStr);
            //TODO:: request game api
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("playerId",playerId);
            jsonObject.put("status","正常");
            model.addAttribute("player",jsonObject);
        }
        return "/player/playerInfo";
    }

    @RequestMapping("/rechargeList")
    public String playerRechargeInfoList(HttpServletRequest request,Model model,
                                         @RequestParam(value = "playerId", required = true) int playerId,
                                         @RequestParam(value = "pageNum",defaultValue = "0")int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        HttpSession session = request.getSession();
        Map<String,Integer> parameterMap = Maps.newHashMap();
        int startNum = pageNum * pageSize;
        int endNum = (pageNum+1) * pageSize;
        parameterMap.put("playerId",playerId);
        //parameterMap.put("startNum",startNum);
        //parameterMap.put("endNum",endNum);
        List<PlayerRechargeInfo> result = playerRechargeInfoService.getByPage(parameterMap);
        int count = playerRechargeInfoService.getCount(parameterMap);
        Page<PlayerRechargeInfo> page = new Page<>();
        page.setResult(result);
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setCount(count);

        model.addAttribute("page",page);
        return "/player/rechargeinfolist";

    }

    @RequestMapping("/preRecharge")
    public String preRecharge(HttpServletRequest request,Model model,
                              @RequestParam(value = "playerId",required = true) int playerId){

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(Constant.getInstance().USER_ID);
        User user = userService.findById(userId);
        RechargeSend rechargeSend = Constant.getInstance().getRechargeSendMap().get(user.getLevel());
        if (rechargeSend != null){
            model.addAttribute("chargeNum",rechargeSend.getRechargeNum());
            model.addAttribute("sendNum",rechargeSend.getReturnNum());
        }else {
            model.addAttribute("chargeNum",0);
            model.addAttribute("sendNum",0);
        }
        model.addAttribute("countDiamond",user.getDiamond());
        //TODO request game api
        model.addAttribute("playerId",playerId);
        model.addAttribute("nickName","煞雕");
        Map<Integer,Integer> rechargeMap = Constant.getInstance().getRechargeMap();
        model.addAttribute("rechargeMap",rechargeMap);
        return "/player/recharge";
    }
    @RequestMapping("/recharge")
    public String recharge(HttpServletRequest request,Model model,
                           @RequestParam(value = "playerId",required = true) int playerId,
                           @RequestParam(value = "rechargeNum",required = true) int rechargeNum){
        Integer userId = (Integer) request.getSession().getAttribute(Constant.getInstance().USER_ID);
        User user = userService.findById(userId);
        PlayerRechargeInfo playerRechargeInfo = new PlayerRechargeInfo();
        int level = user.getLevel();
        if (level <= 10){
            RechargeSend rechargeSend = Constant.getInstance().getRechargeSendMap().get(level);
            int sendNum = rechargeNum/rechargeSend.getRechargeNum() * rechargeSend.getReturnNum();
            playerRechargeInfo.setSendNum(sendNum);

        }else {
            playerRechargeInfo.setSendNum(0);
        }
        playerRechargeInfo.setPlayerId(playerId);
        playerRechargeInfo.setRechargeNum(rechargeNum);
        playerRechargeInfo.setAgentId(user.getId());
        playerRechargeInfo.setRechargeTime(new Date());
        //TODO  request game api
        playerRechargeInfoService.add(playerRechargeInfo);

        //TODO:: request game api
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("playerId", playerId);
        jsonObject.put("status", "正常");
        model.addAttribute("player", jsonObject);
        return "/player/playerInfo";
    }

}

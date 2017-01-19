package com.oooo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;
import com.baidu.jprotobuf.pbrpc.client.ProtobufRpcProxy;
import com.baidu.jprotobuf.pbrpc.transport.RpcClient;
import com.google.common.collect.Maps;
import com.mahjong.gate.entity.Player;
import com.mahjong.hessian.GateServer;
import com.mahjong.hessian.IRemoteService;
import com.mahjong.util.HessianUtil;
import com.oooo.model.PlayerRechargeInfo;
import com.oooo.model.RechargeSend;
import com.oooo.model.User;
import com.oooo.rpc.MsgInfo;
import com.oooo.rpc.recharge.Recharge;
import com.oooo.rpc.recharge.RechargeRpcService;
import com.oooo.service.PlayerRechargeInfoService;
import com.oooo.service.UserService;
import com.oooo.util.Constant;
import com.oooo.util.Page;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/agent/playerRecharge")
public class PlayerRechargeInfoController {
    private static Logger logger = LoggerFactory.getLogger(PlayerRechargeInfoController.class);
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
        IRemoteService remoteService = HessianUtil.getLobbyRemoteService(Constant.getInstance().lobby_server);
        Player player = null;
        try {
            player = remoteService.getPlayerByAccountId(playerId);
        }catch (Exception e){
            logger.info("precharge ===== remoteService get player by account id error" + e);
            model.addAttribute(Constant.getInstance().error_msg,"玩家ID不存在");
            return "/error404";
        }
        if (player == null){
            model.addAttribute(Constant.getInstance().error_msg,"玩家ID不存在");
            return "/error404";
        }
        RechargeSend rechargeSend = Constant.getInstance().getRechargeSendMap().get(user.getLevel());
        if (rechargeSend != null){
            model.addAttribute("chargeNum",rechargeSend.getRechargeNum());
            model.addAttribute("sendNum",rechargeSend.getReturnNum());
        }else {
            model.addAttribute("chargeNum",0);
            model.addAttribute("sendNum",0);
        }
        model.addAttribute("countDiamond",user.getDiamond());
        model.addAttribute("playerId",playerId);
        model.addAttribute("nickName",player.getRoleName());
        Map<Integer,Integer> rechargeMap = Constant.getInstance().getRechargeMap();
        model.addAttribute("rechargeMap",rechargeMap);
        return "/player/recharge";
    }
    @RequestMapping("/recharge")
    public String recharge(HttpServletRequest request,Model model,
                           @RequestParam(value = "playerId",required = true) int playerId,
                           @RequestParam(value = "rechareNum",required = true) int rechargeNum){
        Integer userId = (Integer) request.getSession().getAttribute(Constant.getInstance().USER_ID);
        User user = userService.findById(userId);
        PlayerRechargeInfo playerRechargeInfo = new PlayerRechargeInfo();
        int level = user.getLevel();
        int surplusDiamond = user.getDiamond() - rechargeNum;
        if (surplusDiamond < 0){
            model.addAttribute(Constant.getInstance().error_msg,"你的钻石不足");
            return "/error404";
        }
        int sendNum  = 0;
        if (level <= 10){
            RechargeSend rechargeSend = Constant.getInstance().getRechargeSendMap().get(level);
            sendNum = rechargeNum/rechargeSend.getRechargeNum() * rechargeSend.getReturnNum();
            playerRechargeInfo.setSendNum(sendNum);

        }else {
            playerRechargeInfo.setSendNum(0);
        }
        playerRechargeInfo.setPlayerId(playerId);
        playerRechargeInfo.setRechargeNum(rechargeNum);
        playerRechargeInfo.setAgentId(user.getId());
        playerRechargeInfo.setRechargeTime(new Date());
        IRemoteService remoteService = HessianUtil.getLobbyRemoteService(Constant.getInstance().lobby_server);
        int gateServerId = 0;
        try {
            Player player = remoteService.getPlayerByAccountId(playerId);
            gateServerId = player.getGateServerId();
            if (player == null){
                model.addAttribute(Constant.getInstance().error_msg,"玩家不存在,请检查玩家ID");
                logger.info("recharge error gateService is null===="+gateServerId);
                return "/error404";
            }
        }catch (Exception e){

        }
        GateServer gateServer = remoteService.getGateServerById(gateServerId);
        if (gateServer == null){
            model.addAttribute(Constant.getInstance().error_msg,"充值失败,请联系管理员");
            return "/error404";
        }
        RpcClient rpcClient = new RpcClient();
        ProtobufRpcProxy<RechargeRpcService> pbrpcProxy = new ProtobufRpcProxy<>(rpcClient,RechargeRpcService.class);
        pbrpcProxy.setHost(gateServer.getRpcIp());
        pbrpcProxy.setPort(gateServer.getRpcPort());

        RechargeRpcService rechargeRpcService = pbrpcProxy.proxy();
        Recharge recharge = new Recharge();
        recharge.setAccountId(playerId);
        recharge.setDiamondNum(rechargeNum+sendNum);
        MsgInfo msgInfo = new MsgInfo();
        msgInfo.setMessage(JSON.toJSONString(recharge));
        try {
            rechargeRpcService.recharge(msgInfo);
        }catch (Exception e){
            logger.info("rpc recharge info error GameServer===="+gateServer.getServerIp());
            try {
                remoteService.rechargeDiamond(playerId, rechargeNum + sendNum);
            }catch (Exception ex){
                logger.info("lobby recharge fail......."+ex);
                model.addAttribute(Constant.getInstance().error_msg,"充值失败,请联系管理员");
                return "/error404";
            }
        }
        user.setDiamond(surplusDiamond);
        userService.updateUser(user);
        playerRechargeInfoService.add(playerRechargeInfo);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("playerId", playerId);
        jsonObject.put("status", "正常");
        model.addAttribute("player", jsonObject);
        return "/player/playerInfo";
    }

}

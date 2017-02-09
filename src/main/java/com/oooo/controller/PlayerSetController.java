package com.oooo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.jprotobuf.pbrpc.client.ProtobufRpcProxy;
import com.baidu.jprotobuf.pbrpc.transport.RpcClient;
import com.google.common.collect.Maps;
import com.mahjong.gate.entity.Player;
import com.mahjong.hessian.GateServer;
import com.mahjong.hessian.IRemoteService;
import com.mahjong.util.HessianUtil;
import com.oooo.rpc.MsgInfo;
import com.oooo.rpc.playerset.UpPlayerSetRpcService;
import com.oooo.service.PlayerRechargeInfoService;
import com.oooo.util.Constant;
import com.oooo.util.RespMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by chenpan on 17-2-8.
 */
@Controller
@RequestMapping("/admin")
public class PlayerSetController {
    private static Logger logger = LoggerFactory.getLogger(PlayerSetController.class);
    public String playerDiamond() {
        return "";
    }

    @RequestMapping("/page")
    public String playerSetPage(Model model) {
        model.addAttribute("isAdmin",true);
        return "/player/playerSetInfo";
    }

    @RequestMapping("/player/{playerId}")
    public String getPlayerInfo(Model model,
                                @PathVariable("playerId") long playerId) {
        IRemoteService remoteService = HessianUtil.getLobbyRemoteService(Constant.getInstance().lobby_server);
        Player player = null;
        try {
            player = remoteService.getPlayerByAccountId(playerId);
        } catch (Exception e) {
            logger.info("getPlayerInfo ===== remoteService get player by account id error" + e);
            model.addAttribute(Constant.getInstance().error_msg, "远程连接错误");
            model.addAttribute("e", e.getMessage());
            return "/error404";
        }
        if (player == null) {
            model.addAttribute(Constant.getInstance().error_msg, "玩家ID不存在");
            return "/error404";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("playerId", player.getAccountId());
        jsonObject.put("status", "正常");
        jsonObject.put("doubleIntegral",player.getDoubleIntegral());
        jsonObject.put("noConsumeDiamond",player.getNoConsumeDiamond());
        model.addAttribute("player", jsonObject);
        return "/player/playerSetInfo";
    }

    @RequestMapping("/playerSet")
    @ResponseBody
    public  RespMsg upPlayerSet(@RequestParam(value = "playerId") long playerId,
                                @RequestParam(value = "setType") int setType,
                                @RequestParam(value = "setValue") int setValue){
        RespMsg<String> respMsg = new RespMsg<>();
        respMsg.setCode(200);
        IRemoteService remoteService = HessianUtil.getLobbyRemoteService(Constant.getInstance().lobby_server);
        Player player = null;
        try {
            player = remoteService.getPlayerByAccountId(playerId);
        } catch (Exception e) {
            logger.info("getPlayerInfo ===== remoteService get player by account id error" + e);
            respMsg.setCode(300);
            respMsg.setMsg("连接错误");
        }
        if (player == null) {
            respMsg.setCode(300);
            respMsg.setMsg("玩家不存在,请检查");
        }

        GateServer gateServer = remoteService.getGateServerById(player.getGateServerId());
        if (gateServer == null){
            respMsg.setCode(300);
            respMsg.setMsg("修改玩家设置失败01");
        }
        RpcClient rpcClient = new RpcClient();
        ProtobufRpcProxy<UpPlayerSetRpcService> pbrpcProxy = new ProtobufRpcProxy<>(rpcClient,UpPlayerSetRpcService.class);
        pbrpcProxy.setHost(gateServer.getRpcIp());
        pbrpcProxy.setPort(gateServer.getRpcPort());

        UpPlayerSetRpcService upPlayerSetRpcService = pbrpcProxy.proxy();
        JSONObject paramJsonObject = new JSONObject();
        paramJsonObject.put("accountId",playerId);
        paramJsonObject.put("setType",setType);
        paramJsonObject.put("setValue",setValue);

        MsgInfo msgInfo = new MsgInfo();
        msgInfo.setMessage(paramJsonObject.toJSONString());
        try {
            msgInfo = upPlayerSetRpcService.upPlayerSet(msgInfo);
            if (msgInfo.getResp() == "200"){
                respMsg.setResult(String.valueOf(setType));
            }
        }catch (Exception e){
            logger.info("rpc recharge info error GameServer===="+gateServer.getServerIp());
            e.printStackTrace();
            try {

            }catch (Exception ex){
                logger.info("lobby recharge fail......."+ex);

            }
            respMsg.setCode(300);
            respMsg.setMsg("修改玩家设置失败02");
        }

        return respMsg;
    }
}

package com.oooo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baidu.jprotobuf.pbrpc.client.ProtobufRpcProxy;
import com.baidu.jprotobuf.pbrpc.transport.RpcClient;
import com.google.common.collect.Lists;
import com.mahjong.gate.entity.Player;
import com.mahjong.hessian.GateServer;
import com.mahjong.hessian.IRemoteService;
import com.mahjong.lobby.entity.CommonActive;
import com.mahjong.util.DateUtil;
import com.mahjong.util.HessianUtil;
import com.mahjong.util.JsonUtil;
import com.oooo.rpc.MsgInfo;
import com.oooo.rpc.playerset.UpPlayerSetRpcService;
import com.oooo.util.Constant;
import com.oooo.util.RespMsg;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenpan on 17-2-8.
 @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
 */
@Controller
@RequestMapping("/admin")
public class CommonActiveController {
    private static Logger logger = LoggerFactory.getLogger(CommonActiveController.class);
    public String playerDiamond() {
        return "";
    }

  /*  @RequestMapping("/commonActive")
    public String playerSetPage(Model model) {
        model.addAttribute("isAdmin",true);
        return "/player/playerSetInfo";
    }*/

    @RequestMapping("/commonActive")
    public String getPlayerInfo(Model model
                               ) {
        IRemoteService remoteService = HessianUtil.getLobbyRemoteService(Constant.getInstance().lobby_server);
        try {
//            player = remoteService.getPlayerByAccountId(playerId);
            List<String> commonActiveStringList = remoteService.getCommonActive();
            List<CommonActive> commonActiveList = Lists.newArrayList();
            for (String commonActiveString : commonActiveStringList) {
                CommonActive commonActive = JsonUtil.unSerializable(CommonActive.class,commonActiveString);
                commonActiveList.add(commonActive);
            }
            model.addAttribute("commonActives",commonActiveList);
        } catch (Exception e) {
            logger.info("getPlayerInfo ===== remoteService get player by account id error" + e);
            model.addAttribute(Constant.getInstance().error_msg, "远程连接错误");
            model.addAttribute("e", e.getMessage());
            return "/error404";
        }

        return "/member/commonActive";
    }

    @RequestMapping("/upCommonActive")
    @ResponseBody
    public  RespMsg upPlayerSet(@RequestParam(value = "type") int type,
                                @RequestParam(value = "state") int state,
                                @RequestParam(value = "startTime") String startTime,
                                @RequestParam(value = "endTime") String endTime){
        RespMsg<String> respMsg = new RespMsg<>();
        respMsg.setCode(200);

        try {
            Date startDate = DateUtils.parseDate(startTime,"yyyy-MM-dd HH:ss:mm");
        } catch (ParseException e) {
            e.printStackTrace();
            respMsg.setCode(300);
            respMsg.setMsg("开始时间格式不正确");
            return respMsg;
        }
        try {
            Date endDate = DateUtils.parseDate(endTime,"yyyy-MM-dd HH:ss:mm");
        } catch (ParseException e) {
            e.printStackTrace();
            respMsg.setCode(300);
            respMsg.setMsg("结束时间格式不正确");
            return respMsg;
        }
        IRemoteService remoteService = HessianUtil.getLobbyRemoteService(Constant.getInstance().lobby_server);
        try {
        } catch (Exception e) {
            logger.info("getPlayerInfo ===== remoteService get player by account id error" + e);
            respMsg.setCode(300);
            respMsg.setMsg("连接错误");
        }
        Map<String,String> reqParam = new HashMap<>();
        reqParam.put("type",String.valueOf(type));
        reqParam.put("value",String.valueOf(state));
        reqParam.put("startDate",startTime);
        reqParam.put("endDate",endTime);
        boolean success = remoteService.updateCommonActive(reqParam);
        if (success == false){
            respMsg.setCode(300);
            respMsg.setMsg("修改玩家设置失败01");
        }


        return respMsg;
    }
}

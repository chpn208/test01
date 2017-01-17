package com.oooo.controller;

import com.alibaba.fastjson.JSON;
import com.baidu.jprotobuf.pbrpc.client.ProtobufRpcProxy;
import com.baidu.jprotobuf.pbrpc.transport.RpcClient;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mahjong.hessian.GateServer;
import com.mahjong.hessian.IRemoteService;
import com.mahjong.util.HessianUtil;
import com.oooo.model.Notice;
import com.oooo.model.User;
import com.oooo.rpc.MsgInfo;
import com.oooo.rpc.notice.NoticeRpcService;
import com.oooo.service.NoticeService;
import com.oooo.service.UserService;
import com.oooo.util.Constant;
import com.oooo.util.Page;
import com.oooo.util.RespMsg;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by chenpan on 16-12-31.
 */
@Controller
@RequestMapping("/admin")
public class MemberController {
    private static Logger logger = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    UserService userService;

    @Autowired
    NoticeService noticeService;
    @RequestMapping("/list")
    public String getAllMember(Model model, HttpServletRequest request,
                               @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                               @RequestParam(value = "pageSize",defaultValue= "10")int pageSize){
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(Constant.getInstance().USER_ID);
        if (userId != null){
            User user = userService.findById(userId);
            if (user != null && user.getLevel() == 99){
                Page<User> userPage = new Page<>();
                List<User> users = userService.getByPage(user,pageSize,pageNum);
                userPage.setResult(users);

                Map<String,Integer> parameterMap = Maps.newHashMap();
                long count = userService.getCount(parameterMap);
                long pageCount = count%pageSize == 0?count/pageSize : count / pageSize +1;
                userPage.setPageCount((int) pageCount);
                userPage.setPageNum(pageNum+1);
                userPage.setPageSize(pageSize);
                userPage.setCount((int) count);
                model.addAttribute("page",userPage);
            }
        }
        return "/member/list";
    }

    /**
     * 公告编辑
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/notice")
    public String noticeView(HttpServletRequest request,Model model){
        List<Notice> noticeList = Lists.newArrayList(Constant.getInstance().getNoticeMap().values());
        model.addAttribute("notices",noticeList);
        return "/member/notice";
    }

    @RequestMapping("noticeContent")
    @ResponseBody
    public RespMsg<String> regNoticeContent(
                                            @RequestParam(value = "noticeType",required = true)Integer noticeType){
        RespMsg<String> respMsg = new RespMsg<>();

        Notice notice = noticeService.findByType(noticeType);
        if (notice == null){
            respMsg.setCode(201);
            respMsg.setMsg("公告类型不存在");
            return respMsg;
        }
        respMsg.setCode(200);
        respMsg.setMsg(notice.getContent());
        return respMsg;
    }

    @RequestMapping("editNoticeContent")
    @ResponseBody
    public RespMsg<String> editNoticeContent(
                                             @RequestParam(value= "noticeType", required = true) Integer noticeType,
                                             @RequestParam(value = "noticeContent",required = true) String noticeContent){
        RespMsg<String> respMsg = new RespMsg<>();

        Notice notice = noticeService.findByType(noticeType);
        if (notice == null){
            respMsg.setCode(201);
            respMsg.setMsg("公告类型不存在");
            return respMsg;
        }

        notice.setContent(noticeContent);
        noticeService.update(notice);
        IRemoteService service = HessianUtil.getLobbyRemoteService("127.0.0.1");
        Collection<GateServer> gateServerList = service.getGateServerList();
        for (GateServer gateServer : gateServerList) {
            try {
                RpcClient rpcClient = new RpcClient();
                ProtobufRpcProxy<NoticeRpcService> pbrpcProxy = new ProtobufRpcProxy<NoticeRpcService>(rpcClient, NoticeRpcService.class);
                pbrpcProxy.setPort(gateServer.rpcPort);
                pbrpcProxy.setHost(gateServer.getServerIp());

                MsgInfo msgInfo = new MsgInfo();
                msgInfo.setMessage(JSON.toJSONString(notice));
                NoticeRpcService noticeRpcService = pbrpcProxy.proxy();
                MsgInfo respRpcMsg = noticeRpcService.notice(msgInfo);
                String resp = respRpcMsg.getResp();
                if (!StringUtils.equals(resp, "success")) {
                    logger.error("notice update error.......ip{},port{}", gateServer.getRpcIp(), gateServer.getRpcPort());
                }
            }catch (Exception e){
                logger.error("notice update error "+ e);
                e.printStackTrace();
            }
        }
        respMsg.setCode(200);
        respMsg.setMsg(noticeContent);
        return respMsg;
    }
}

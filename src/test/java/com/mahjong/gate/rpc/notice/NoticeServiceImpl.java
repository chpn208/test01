package com.mahjong.gate.rpc.notice;

import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.oooo.rpc.MsgInfo;

/**
 * Created by chenpan on 17-1-16.
 */
public class NoticeServiceImpl {
    @ProtobufRPCService(serviceName = "noticeService",methodName = "notice")
    public MsgInfo info(MsgInfo noticeInfo){
        MsgInfo resp = new MsgInfo();
        System.out.println("msg========"+noticeInfo.getMessage());
        resp.setResp("that's ok!!!");
        return resp;
    }
}

package com.mahjong.gate.rpc.notice;

import com.baidu.jprotobuf.pbrpc.ProtobufRPC;
import com.oooo.rpc.MsgInfo;

/**
 * Created by chenpan on 17-1-16.
 */
public interface NoticeService {
    @ProtobufRPC(serviceName = "noticeService",onceTalkTimeout = 200)
    MsgInfo notice(MsgInfo noticeInfo);
}

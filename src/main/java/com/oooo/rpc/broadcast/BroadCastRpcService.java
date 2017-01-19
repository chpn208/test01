package com.oooo.rpc.broadcast;

import com.baidu.jprotobuf.pbrpc.ProtobufRPC;
import com.oooo.rpc.MsgInfo;

/**
 * Created by chenpan on 17-1-19.
 */
public interface BroadCastRpcService {
    @ProtobufRPC(serviceName = "broadCastRpcService",onceTalkTimeout = 200)
    MsgInfo broadCast(MsgInfo msgInfo);
}

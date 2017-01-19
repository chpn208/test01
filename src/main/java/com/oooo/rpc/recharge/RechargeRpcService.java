package com.oooo.rpc.recharge;

import com.baidu.jprotobuf.pbrpc.ProtobufRPC;
import com.oooo.rpc.MsgInfo;

/**
 * Created by chenpan on 17-1-16.
 */
public interface RechargeRpcService {
    @ProtobufRPC(serviceName = "rechargeService", onceTalkTimeout = 200)
    MsgInfo recharge(MsgInfo msgInfo);
}

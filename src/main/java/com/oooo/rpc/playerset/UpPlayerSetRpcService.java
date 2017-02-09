package com.oooo.rpc.playerset;

import com.baidu.jprotobuf.pbrpc.ProtobufRPC;
import com.oooo.rpc.MsgInfo;

/**
 * Created by chenpan on 17-2-8.
 */
public interface UpPlayerSetRpcService {
    @ProtobufRPC(serviceName = "upPlayerSetService", onceTalkTimeout = 200)
    MsgInfo upPlayerSet(MsgInfo msgInfo);
}

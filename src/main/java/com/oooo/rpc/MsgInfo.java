package com.oooo.rpc;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * Created by chenpan on 17-1-16.
 */
public class MsgInfo {
    @Protobuf
    private String message;
    @Protobuf
    private String resp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }
}

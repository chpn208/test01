package rpc;

import com.baidu.jprotobuf.pbrpc.transport.RpcServer;
import com.mahjong.gate.rpc.notice.NoticeServiceImpl;

import java.net.InetSocketAddress;

/**
 * Created by chenpan on 17-1-16.
 */
public class Server{
    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        NoticeServiceImpl noticeService = new NoticeServiceImpl();
        rpcServer.registerService(noticeService);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("0.0.0.0",1988);
        rpcServer.start(inetSocketAddress);
//        rpcServer.start(1988);
    }
}

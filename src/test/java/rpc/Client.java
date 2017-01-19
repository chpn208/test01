package rpc;

import com.alibaba.fastjson.JSON;
import com.baidu.jprotobuf.pbrpc.client.ProtobufRpcProxy;
import com.baidu.jprotobuf.pbrpc.transport.RpcClient;
import com.oooo.rpc.MsgInfo;
import com.oooo.rpc.recharge.Recharge;
import com.oooo.rpc.recharge.RechargeRpcService;


/**
 * Created by chenpan on 17-1-16.
 */
public class Client {
    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient();
        ProtobufRpcProxy<RechargeRpcService> pbrpcProxy = new ProtobufRpcProxy<RechargeRpcService>(rpcClient,RechargeRpcService.class);
        pbrpcProxy.setPort(1988);
        pbrpcProxy.setHost("127.0.0.1");
        RechargeRpcService rechargeRpcService = pbrpcProxy.proxy();
        MsgInfo request = new MsgInfo();
        Recharge recharge = new Recharge();
        recharge.setAccountId(1111);
        recharge.setDiamondNum(555);
        request.setMessage(JSON.toJSONString(recharge));
        MsgInfo resp = rechargeRpcService.recharge(request);
        System.out.println(resp.getResp());
        rpcClient.stop();
     /*   IRemoteService service = HessianUtil.getLobbyRemoteService("127.0.0.1");
        Collection<GateServer> gateServerList = service.getGateServerList();
        System.out.println(gateServerList.size());
        for (GateServer gateServer : gateServerList) {
*/

      /*  RpcClient rpcClient = new RpcClient();
        ProtobufRpcProxy<NoticeRpcService> pbrpcProxy = new ProtobufRpcProxy<NoticeRpcService>(rpcClient,NoticeRpcService.class);
        pbrpcProxy.setPort(1988);
        pbrpcProxy.setHost("127.0.0.1");
        NoticeRpcService noticeService = pbrpcProxy.proxy();
        MsgInfo request = new MsgInfo();
        Notice notice = new Notice();
        notice.setType(1);
        notice.setContent("fafdafdfa");
        request.setMessage(JSON.toJSONString(notice));
        MsgInfo resp = noticeService.notice(request);
        System.out.println(resp.getResp());
        rpcClient.stop();*/
      /*}*/
    }
}

package com.oooo.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.mahjong.util.JsonUtil;
import com.oooo.model.Notice;
import com.oooo.model.Permissions;
import com.oooo.model.RechargeSend;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenpan on 16-12-31.
 */
public class Constant {
    @Autowired
    private SerialUtil serialUtil;
    private static Constant constant = new Constant();
    public static Constant getInstance(){
        return constant;
    }

    public final String USER_ID="userName";
    public final String lobby_server = "127.0.0.1";
    public final String error_msg = "errorMsg";
    public final Properties properties;

    public final Map<String,List<HttpSession>> sessionMap = new ConcurrentHashMap<>();
    private Constant(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        serialUtil = (SerialUtil) applicationContext.getBean("serialUtil");
        properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(this.getClass().getResource("/").getFile()+"application.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Map<Integer,RechargeSend> rechargeSendMap = Maps.newHashMap();
    private Map<String,Permissions> permissionsUrlMap = Maps.newHashMap();
    private Map<Integer,Permissions> permissionsMap = Maps.newHashMap();

    private Map<Integer,Integer> rechargeMap = Maps.newHashMap();
    private Map<Integer,Notice> noticeMap = Maps.newHashMap();
//    private AtomicInteger usrId = new AtomicInteger(100000);

    public Map<Integer, RechargeSend> getRechargeSendMap() {
        return rechargeSendMap;
    }

    public void setRechargeSendMap(Map<Integer, RechargeSend> rechargeSendMap) {
        this.rechargeSendMap = rechargeSendMap;
    }

    public Map<String, Permissions> getPermissionsUrlMap() {
        return permissionsUrlMap;
    }

    public Map<Integer,Notice> getNoticeMap(){
        return noticeMap;
    }

    public void setPermissionsUrlMap(Map<String, Permissions> permissionsUrlMap) {
        this.permissionsUrlMap = permissionsUrlMap;
    }

    public void initPermissionMap(){
        try {

            List<Map<String, Object>> permissionsObjMap = serialUtil.getBySQL("select * from permissions");
            for (Map<String, Object> stringObjectMap : permissionsObjMap) {
                Permissions permission = new Permissions();
                Integer level = (Integer) stringObjectMap.get("level");
                String desc = (String) stringObjectMap.get("desc");
                String urlPrefix = (String) stringObjectMap.get("url_prefix");
                if (StringUtils.isEmpty(desc) || StringUtils.isEmpty(urlPrefix)) {
                    throw new Exception("name or urlPrefix is null");
                }
                permission.setLevel(level);
                permission.setUrlPrefix(urlPrefix);
                permission.setDesc(desc);
                Permissions oldPermission = permissionsUrlMap.get(permission.getUrlPrefix());
                if (oldPermission == null ||oldPermission.getLevel() > permission.getLevel()){
                    permissionsUrlMap.put(permission.getUrlPrefix(),permission);
                }
                permissionsMap.put(permission.getLevel(),permission);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    
    public void initRechargeSendMap(){
        List<Map<String,Object>> rechargeSendObjMap = 
                serialUtil.getBySQL("select * from recharge_send");
        for (Map<String, Object> stringObjectMap : rechargeSendObjMap) {
            Integer level = (Integer) stringObjectMap.get("level");
            Integer rechargeNum = (Integer) stringObjectMap.get("recharge_num");
            Integer returnNum = (Integer) stringObjectMap.get("return_num");
            if (level == null || rechargeNum == null || returnNum == null){
                throw new NullPointerException("rechargeSend properties has a null value");
            }
            RechargeSend rechargeSend = new RechargeSend();
            rechargeSend.setLevel(level);
            rechargeSend.setRechargeNum(rechargeNum);
            rechargeSend.setReturnNum(returnNum);
            rechargeSendMap.put(rechargeSend.getLevel(), rechargeSend);
        }
    }
    public void initRechargeMap(){
        rechargeMap.put(1,50);
        rechargeMap.put(2,100);
        rechargeMap.put(3,200);
    }
    public void initNoticeType(){
        List<Map<String, Object>> result = serialUtil.getBySQL("select * from notice");
        System.out.println("--------------initNoticeType      result.........."+result);
        for (Map<String, Object> stringObjectMap : result) {
            Integer type = (Integer) stringObjectMap.get("type");
            String name= (String) stringObjectMap.get("name");
            String content = (String) stringObjectMap.get("content");

            Notice notice = new Notice();
            notice.setType(type);
            notice.setName(name);
            notice.setContent(content);
            System.out.println(JSONObject.toJSONString(notice));

            noticeMap.put(notice.getType(),notice);
        }
    }

   /* public void initUserId (){
        List<Map<String, Object>> result = serialUtil.getBySQL("select max(id)  from user");
        Map<String, Object> map = result.get(0);
        int maxUserId = (int) map.get("max(id)");
        if (maxUserId > userId.get()){
            userId.set(maxUserId);
        }
    }*/
  /*  public int getUserId(){
        return userId.addAndGet(1);
    }*/
    public Map<Integer,Integer> getRechargeMap(){
        return rechargeMap;
    }
    public Map<Integer,Permissions> getPermissionsMap(){
        return permissionsMap;
    }

    public SerialUtil getSerialUtil(){
        return serialUtil;
    }
}

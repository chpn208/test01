package com.oooo.util;

import com.google.inject.internal.Maps;
import com.oooo.model.Permissions;
import com.oooo.model.RechargeSend;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

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


    private Constant(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        serialUtil = (SerialUtil) applicationContext.getBean("serialUtil");
    }
    private Map<Integer,RechargeSend> rechargeSendMap = Maps.newHashMap();
    private Map<String,Permissions> permissionsMap = Maps.newHashMap();

    public Map<Integer, RechargeSend> getRechargeSendMap() {
        return rechargeSendMap;
    }

    public void setRechargeSendMap(Map<Integer, RechargeSend> rechargeSendMap) {
        this.rechargeSendMap = rechargeSendMap;
    }

    public Map<String, Permissions> getPermissionsMap() {
        return permissionsMap;
    }

    public void setPermissionsMap(Map<String, Permissions> permissionsMap) {
        this.permissionsMap = permissionsMap;
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
                permissionsMap.put(permission.getUrlPrefix(),permission);

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
}

/**
 * Created by chenpan on 16-12-31.
 */
package com.oooo.listener;

import com.oooo.util.Constant;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CacheListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("------------------------------------------initPermissionMap");
        Constant.getInstance().initPermissionMap();
        System.out.println("-------------------------------------------initRechargeSendMap");
        Constant.getInstance().initRechargeSendMap();
        System.out.println("--------------------------------------------initRechargeMap");
        Constant.getInstance().initRechargeMap();
        System.out.println("----------------------------------------------initNoticeType");
        Constant.getInstance().initNoticeType();
        /*System.out.println("--------------------------------------------------initUserId");
        Constant.getInstance().initUserId();*/
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

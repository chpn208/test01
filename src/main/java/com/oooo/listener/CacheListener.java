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
        Constant.getInstance().initPermissionMap();
        Constant.getInstance().initRechargeSendMap();
        Constant.getInstance().initRechargeMap();
        Constant.getInstance().initNoticeType();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

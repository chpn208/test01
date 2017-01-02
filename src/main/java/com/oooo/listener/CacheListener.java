package com.oooo.listener; /**
 * Created by chenpan on 16-12-31.
 */

import com.oooo.util.Constant;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class CacheListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Constant.getInstance().initPermissionMap();
        Constant.getInstance().initRechargeSendMap();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

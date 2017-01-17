package com.oooo.filter;

import com.oooo.model.Permissions;
import com.oooo.model.User;
import com.oooo.service.PlayerRechargeInfoService;
import com.oooo.service.UserService;
import com.oooo.util.Constant;
import com.oooo.util.SerialUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

/**
 * Created by chenpan on 16-12-31.
 */
public class PermissionsFilter implements Filter {
    @Autowired
    UserService userService;
    @Autowired
    PlayerRechargeInfoService playerRechargeInfoService;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        String getRequestURL =request.getRequestURI().toString();
        String[] requestDatas = getRequestURL.split("/");
       /* if (requestDatas.length == 0){
            chain.doFilter(req,resp);
        }*/
        if (requestDatas.length > 0) {
            String permissionUri = requestDatas[1];
            Permissions permission = Constant.getInstance().getPermissionsMap().get(permissionUri);
            HttpServletResponse response = (HttpServletResponse) resp;
            if (permission != null) {
                HttpSession session = request.getSession();
                Integer userId = (Integer) session.getAttribute(Constant.getInstance().USER_ID);
                if (userId == null) {
                    response.sendRedirect("/");
                    return;
                }
                SerialUtil serialUtil = Constant.getInstance().getSerialUtil();
                List<Map<String, Object>> result = serialUtil.getBySQL("select * from user u where u.id =" + userId);

                if (result == null || result.size() == 0) {
                    response.sendRedirect("/");
                    return;
                }
                Integer userLevel = (Integer) result.get(0).get("level");
                if (userLevel < permission.getLevel()) {
                    response.sendError(504,"您没有该权限");
                    return;
                }
            }
        }

        chain.doFilter(req,resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

package com.oooo.filter;

import com.oooo.model.Permissions;
import com.oooo.model.User;
import com.oooo.service.UserService;
import com.oooo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by chenpan on 16-12-31.
 */
public class PermissionsFilter implements Filter {
    @Autowired
    UserService userService;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        String getRequestURL =request.getRequestURL().toString();
        String[] requestDatas = getRequestURL.split("/");
        String permissionUri = requestDatas[0];
        Permissions permission = Constant.getInstance().getPermissionsMap().get(permissionUri);
        if (permission != null){
            HttpSession session = request.getSession();
            String userName = (String) session.getAttribute("loginName");
            if (userName == null){
                return;
            }
            User user = userService.findByName(userName) ;
            if (user == null){
                return;
            }
            if (user.getLevel() < permission.getLevel()){
                return;
            }
        }

        chain.doFilter(req,resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

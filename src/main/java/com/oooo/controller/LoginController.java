package com.oooo.controller;

import com.oooo.model.Menu;
import com.oooo.model.User;
import com.oooo.service.MenuService;
import com.oooo.service.UserService;
import com.oooo.util.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */
@Controller
@RequestMapping("home")
public class LoginController {
    @Autowired
    MenuService menuService;
    @Autowired
    UserService userService;
    @RequestMapping("/login")
    public String login(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        //Integer userId = session.getAttribute("userId");
        Integer userId = (Integer) request.getSession().getAttribute(Constant.getInstance().USER_ID);
        if (userId == null){//未登陆
            String userIdStr = request.getParameter("userName");
            if (StringUtils.isEmpty(userIdStr) || !StringUtils.isNumeric(userIdStr)){
                return "/register";
            }else {
                userId = Integer.parseInt(userIdStr);
                User user = userService.findById(userId);
                String password = request.getParameter("password");
                if (user != null && user.getPassword().equals(password)){
                    session.setAttribute(Constant.getInstance().USER_ID, user.getId());
                    List<Menu> menus = menuService.getMenusByUser(user);
                    model.addAttribute("menus", menus);
                    return "/index";
                }
            }

        }else {
            User user = userService.findById(userId);
            if (user != null) {
                session.setAttribute(Constant.getInstance().USER_ID, user.getId());
                List<Menu> menus = menuService.getMenusByUser(user);
                model.addAttribute("menus", menus);
                return "/index";
            }
        }
        return "/../../login";
    }
}

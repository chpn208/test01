package com.oooo.controller;

import com.oooo.model.Menu;
import com.oooo.model.User;
import com.oooo.service.MenuService;
import com.oooo.service.UserService;
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
        User user = userService.findById(1);
        if (user != null){
            session.setAttribute("username",user.getName());
            List<Menu> menus = menuService.getMenusByUser(user);
            model.addAttribute("menu",menus);
            return "/index";
        }else {
            return "/login";
        }
    }
}

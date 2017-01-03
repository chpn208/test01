package com.oooo.controller;

import com.oooo.model.User;
import com.oooo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by chenpan on 16-12-31.
 */
@Controller
@RequestMapping("member")
public class MemberController {
    @Autowired
    UserService userService;
    @RequestMapping("/list")
    public String getAllMember(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (StringUtils.isNotEmpty(username)){
            User user = userService.findByName(username);
            if (user != null && user.getLevel() == 99){
                List<User> users = userService.findMembers();
                model.addAttribute("users",users);
            }
        }
        return "/member/list";
    }
}

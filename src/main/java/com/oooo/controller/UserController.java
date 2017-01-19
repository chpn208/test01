package com.oooo.controller;

import com.oooo.model.User;
import com.oooo.service.UserService;
import com.oooo.util.Constant;
import com.oooo.util.RespMsg;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.PrivateKey;

/**
 * Created by chenpan on 17-1-8.
 */
@Controller
@RequestMapping("/agent")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserService userService;

    @RequestMapping("/userInfo")
    public String getUserInfo(HttpServletRequest request, Model model){
        Integer userId = (Integer) request.getSession().getAttribute(Constant.getInstance().USER_ID);

        User user = userService.findById(userId);

        if (user == null){
            model.addAttribute(Constant.getInstance().error_msg,"用户不存在");
            return "/error404";
        }
        model.addAttribute("userId",user.getId());
        model.addAttribute("userName",user.getName());
        model.addAttribute("wxNumber",user.getWechart());
        model.addAttribute("mobile", user.getMobile());
        model.addAttribute("diamond",user.getDiamond());
        return "/agent/userInfo";
    }

    @RequestMapping("userPasswordInfo")
    public String passwordInfo(HttpServletRequest request){
        return "/agent/userPassword";
    }

    @RequestMapping("updatePassword")
    @ResponseBody
    public RespMsg<String> updatePassword(HttpServletRequest request,
                                           @RequestParam(value = "oldpassword",required = true)String oldpassword,
                                           @RequestParam(value = "password",required = true) String password,
                                          @RequestParam(value = "repassword",required = true)String repassword){

        RespMsg<String> respMsg = new RespMsg<>();
        if (StringUtils.isEmpty(password)){
            respMsg.setCode(201);
            respMsg.setMsg("密码不能为空");
            return respMsg;
        }

        if (StringUtils.length(password) > 16){
            respMsg.setCode(201);
            respMsg.setMsg("密码不能超过16位数");
            return respMsg;
        }

        if (!StringUtils.equals(password,repassword)){
            respMsg.setCode(201);
            respMsg.setMsg("两次输入的密码不一致");
            return respMsg;
        }
        Integer userId = (Integer) request.getSession().getAttribute(Constant.getInstance().USER_ID);

        User user = userService.findById(userId);

        if (user == null){
            respMsg.setCode(201);
            respMsg.setMsg("用户不存在");
            return respMsg;
        }

        if (!StringUtils.equals(user.getPassword(),oldpassword)){
            respMsg.setCode(201);
            respMsg.setMsg("用户密码不正确");
            return respMsg;
        }

        user.setPassword(password);
        userService.updateUser(user);
        respMsg.setCode(200);

        return respMsg;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(Constant.getInstance().USER_ID);
        return "/../../login";
    }
}

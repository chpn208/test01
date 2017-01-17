package com.oooo.controller;

import com.oooo.model.User;
import com.oooo.service.UserService;
import com.oooo.util.Constant;
import com.oooo.util.EPermissionLevel;
import com.oooo.util.RespMsg;
import com.oooo.util.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by chenpan on 17-1-1.
 */
@Controller
@RequestMapping("")
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping("/checkCode")
    @ResponseBody
    public String checkCode(HttpServletRequest request){
        String validateCode = request.getParameter("validateCode");
        HttpSession session = request.getSession();
        String validationCodeInSession = (String) session.getAttribute("validation_code");
        if (validationCodeInSession.toUpperCase().equals(validateCode.toUpperCase())){
            return "200";
        }
        return "500";
    }

    @RequestMapping("register")
    public String login(HttpServletRequest request, Model model,
                        @RequestParam(value = "c",required = false) String c){
        if (StringUtils.isNotEmpty(c)){
//            User user = userService.findByKeyCode(c);
            Integer userId = userService.findUserIdByKeyCode(c);
            /*if (user != null){
                model.addAttribute("upAgent",user.getId());
            }*/
            model.addAttribute("upAgent",userId);
        }
        return "/register";

    }
    @RequestMapping("/agentAdd.do")
    @ResponseBody
    public RespMsg<String> saveAgent(HttpServletRequest request,
                    @RequestParam(value = "randCode") String randCode,
                    @RequestParam(value = "upAgent",defaultValue = "0") Integer upAgent,
                    @RequestParam(value = "titleName") String titleName,
                    @RequestParam(value = "password") String password,
                    @RequestParam(value = "mobilePhone") String mobile,
                    @RequestParam(value = "weixinCode") String wxCode,
                    @RequestParam(value = "s_province") String province,
                    @RequestParam(value = "s_city") String city,
                    @RequestParam(value = "s_county") String region,
                    @RequestParam(value = "address") String address
                            ){
        HttpSession session = request.getSession();
        RespMsg<String> respMsg = new RespMsg<>();
        String validateCode = (String) session.getAttribute("validation_code");
        if (!validateCode.toUpperCase().equals(randCode.toUpperCase())){
            respMsg.setCode(201);
            respMsg.setMsg("验证码不正确");
            return respMsg;
        }

        if (upAgent != null && upAgent != 0){
            User upAgentUser = userService.findById(upAgent);
            int agentNum = upAgentUser.getAgentNum();
            agentNum+=1;
            upAgentUser.setAgentNum(agentNum);
            userService.updateUser(upAgentUser);
        }
        User user = new User();
        user.setName(titleName);
        user.setPassword(password);
        user.setMobile(Long.parseLong(mobile));
        user.setWechart(wxCode);
        user.setLevel(EPermissionLevel.AGENT_LEVEL_5.getLevel_value());
        if (upAgent != null) {
            user.setParentUser(upAgent);
        }
        user.setProvince(province);
        user.setCity(city);
        user.setCounty(region);
        user.setDetailedAddress(address);
        user.setAgentNum(0);
        User savedUser = userService.addUser(user);
        session.setAttribute(Constant.getInstance().USER_ID,savedUser.getId());

        respMsg.setCode(200);
        respMsg.setResult(user.getId()+"");
        return respMsg;
    }
}

package com.oooo.controller;

import com.oooo.model.User;
import com.oooo.service.UserService;
import com.oooo.util.EPermissionLevel;
import com.oooo.util.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/agentAdd.do")
    public String saveAgent(HttpServletRequest request){
        Map<String, Object> params = ServletUtils.getRequestParameters(request);
        String randCode = (String) params.get("randCodeImage");
        HttpSession session = request.getSession();
        String validateCode = (String) session.getAttribute("validation_code");
        if (!validateCode.toUpperCase().equals(randCode.toUpperCase())){
            return "";
        }
        String upAgent = (String) params.get("upAgent");
        String titleName = (String) params.get("titleName");
        if (StringUtils.isEmpty(titleName)){
            return "";
        }

        String password = (String) params.get("password");
        if (StringUtils.isEmpty(password)){
            return "";
        }
        String mobile = (String) params.get("mobilePhone");
        if (StringUtils.isEmpty(mobile)){
            return "";
        }

        String wxCode = (String) params.get("weixinCode");
        if (StringUtils.isEmpty(wxCode)){
            return "";
        }
        String province = (String) params.get("provinceCode");

        String city = (String) params.get("cityCode");
        String region = (String) params.get("regionCode");
        String address = (String) params.get("address");

        User user = new User();
        user.setName(titleName);
        user.setPassword(password);
        user.setMobile(Long.parseLong(mobile));
        user.setWechart(wxCode);
        user.setLevel(EPermissionLevel.AGENT_LEVEL_5.getLevel_value());
        if (StringUtils.isNotEmpty(upAgent)) {
            user.setParentUser(Integer.parseInt(upAgent));
        }
        user.setProvince(province);
        user.setCity(city);
        user.setCounty(region);
        user.setDetailedAddress(address);

        User savedUser = userService.addUser(user);
        session.setAttribute("loginName",savedUser.getId());



        return "/index";
    }
}

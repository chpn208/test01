package com.oooo.controller;

import com.oooo.model.User;
import com.oooo.service.UserService;
import com.oooo.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getAllMember(Model model, HttpServletRequest request,
                               @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                               @RequestParam(value = "pageSize",defaultValue= "10")int pageSize){
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (StringUtils.isNotEmpty(username)){
            User user = userService.findByName(username);
            if (user != null && user.getLevel() == 99){
                Page<User> userPage = new Page<>();
                List<User> users = userService.getByPage(user,pageSize,pageNum);
                userPage.setResult(users);

                long count = userService.getCount(user);
                long pageCount = count%pageSize == 0?count/pageSize : count / pageSize +1;
                userPage.setPageCount((int) pageCount);
                userPage.setPageNum(pageNum+1);
                userPage.setPageSize(pageSize);
                userPage.setCount((int) count);
                model.addAttribute("page",userPage);
            }
        }
        return "/member/list";
    }
}

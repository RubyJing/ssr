package com.ssm.controller;

import com.ssm.entity.User;
import com.ssm.entity.UserInfo;
import com.ssm.service.UserInfoService;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/addUser")
    public ModelAndView addUser(User user, UserInfo userInfo){
        ModelAndView modelAndView = new ModelAndView();
        String result = "";
        if(StringUtils.isEmpty(user) && StringUtils.isEmpty(userInfo)){
            userService.insertOrUpdateUser(user,userInfo);
            userInfoService.insertOrUpdateUserInfo(userInfo);
            //代表成功
            result = "1";
        }else {
            result = "0";
        }
        //页面地址：/xx/xx.jsp
        modelAndView.setViewName("");
        //页面数据模型信息
        modelAndView.addObject("user",user);
      return modelAndView;
    }
}

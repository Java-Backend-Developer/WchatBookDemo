package com.controller;

import com.entity.User;
import com.response.JsonResult;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/10/6.
 */
@RestController
@RequestMapping("user")
public class UserJsonController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public JsonResult login(HttpSession session, User user){
        user = userService.queryByParam(user);
        if(user!=null){
            session.setAttribute("user_id",user.getId());
        }
        return new JsonResult();

    }
}

package com.controller;

import com.entity.MobileLoginInfo;
import com.entity.User;
import com.response.JsonResult;
import com.service.MobileLoginInfoService;
import com.service.UserService;
import com.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Administrator on 2017/10/6.
 */
@RestController
@RequestMapping("app_user")
public class APPUserJsonController {

    @Autowired
    private UserService userService;
    @Autowired
    private MobileLoginInfoService mobileLoginInfoService;

    @RequestMapping("app_user_check")
    public JsonResult appUserCheck(String username) {
        boolean isExist = userService.queryUserByParam(username);
        if(!isExist)
        return new JsonResult("0", "用户名可以使用！",isExist);
        return new JsonResult("-1", "用户名已存在，请更换！",isExist);
    }

    @RequestMapping("app_user_register")
    public JsonResult appUserRegister(User user) throws Exception {
        boolean resulet = userService.add(user);
        if (resulet)
        return new JsonResult("0","SUCCESS",null);
        return new JsonResult("-1","FAIL",null);

    }


    @RequestMapping("app_login")
    public JsonResult appLogin(HttpServletRequest request, String username, String password) throws Exception {
        User user = userService.queryUserByParam(username, password);
        if(user!=null){
            Enumeration  enumeration = request.getHeaderNames();
            Map<String,Object> map = new HashMap<String, Object>();
            while (enumeration.hasMoreElements()){
                String  headerName = (String) enumeration.nextElement();
                String header = request.getHeader(headerName);
                map.put(headerName,header);
            }
            MobileLoginInfo mobileLoginInfo = (MobileLoginInfo) MapUtil.mapToObject(map,MobileLoginInfo.class);
            mobileLoginInfo.setUser(user);
            mobileLoginInfoService.remove(mobileLoginInfo);
            String appToken = UUID.randomUUID().toString();
            mobileLoginInfo.setAppToken(appToken);
            mobileLoginInfoService.add(mobileLoginInfo);
            return new JsonResult("0","SUCCESS",user);
        }
        return new JsonResult("-1","FAIL",null);
    }

    @RequestMapping("app_login_out")
    public JsonResult appLoginOut(User user) throws Exception {
        MobileLoginInfo mobileLoginInfo = new MobileLoginInfo();
        mobileLoginInfo.setUser(user);
        mobileLoginInfoService.remove(mobileLoginInfo);
        return new JsonResult("0","SUCCESS",null);
    }

    @RequestMapping("app_user_update")
    public JsonResult appUserUpdate(User user) throws Exception {
        boolean resulet = userService.update(user);
        if(resulet)
        return new JsonResult("0","SUCCESS",null);
        return new JsonResult("-1","FAIL",null);
    }
}

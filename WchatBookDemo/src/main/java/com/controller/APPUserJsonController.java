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

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2017/10/6.
 */
@RestController
@RequestMapping("app_user")
public class APPUserJsonController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private MobileLoginInfoService mobileLoginInfoService;

    @RequestMapping("app_user_check")
    public JsonResult appUserCheck(String username) {
        boolean isExist = userService.checkUsername(username);
        if (!isExist)
            return success("用户名可以使用！", false);
        return error("用户名已存在，请更换！", true);
    }

    @RequestMapping("app_user_register")
    public JsonResult appUserRegister(User user) throws Exception {
        boolean resulet = userService.add(user);
        if (resulet)
            return success(null);
        return error();

    }


    @RequestMapping("app_login")
    public JsonResult appLogin(HttpServletRequest request, User user) throws Exception {
        user = userService.queryByParam(user);
        if (user != null) {
            Enumeration enumeration = request.getHeaderNames();
            Map<String, Object> map = new HashMap<String, Object>();
            while (enumeration.hasMoreElements()) {
                String headerName = (String) enumeration.nextElement();
                String header = request.getHeader(headerName);
                map.put(headerName, header);
            }
            MobileLoginInfo mobileLoginInfo = (MobileLoginInfo) MapUtil.mapToObject(map, MobileLoginInfo.class);
            mobileLoginInfo.setUser(user);
            mobileLoginInfoService.remove(mobileLoginInfo);
            String appToken = UUID.randomUUID().toString();
            mobileLoginInfo.setAppToken(appToken);
            mobileLoginInfoService.add(mobileLoginInfo);
            return success(user);
        }
        return error();
    }

    @RequestMapping("app_login_out")
    public JsonResult appLoginOut(User user) throws Exception {
        MobileLoginInfo mobileLoginInfo = new MobileLoginInfo();
        mobileLoginInfo.setUser(user);
        boolean resulet = mobileLoginInfoService.remove(mobileLoginInfo);
        if (resulet)
            return success(null);
        return error();
    }

    @RequestMapping("app_user_update")
    public JsonResult appUserUpdate(User user) throws Exception {
        boolean resulet = userService.update(user);
        if (resulet)
            return success(null);
        return error();
    }
    @RequestMapping("app_user_find")
    public JsonResult findAppUser(User user) throws Exception {
        User data = userService.queryByParam(user);
        return success(data);
    }


    @RequestMapping("app_user_list")
    public JsonResult listAppUser(User user) throws Exception {
        List<User> data = userService.pageByParam(user);
        return success(data);
    }
}

package com.controller;

import com.service.WchatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/9/30.
 */
@Controller
@RequestMapping("wchat/user")
public class WchatUserController {

    @Autowired
    private WchatUserService wchatUserService;

    /**
     * 获取公众号关注用户列表
     * @return
     * @throws Exception
     */
    @RequestMapping("get_user_list")
    @ResponseBody
    public Object getUserList() throws Exception {
       Object resultJson = wchatUserService.getUserList();
        return resultJson;
    }

    /**
     * 根据openid获取用户信息
     * @param openid
     * @return
     * @throws Exception
     */
    @RequestMapping("get_user_info")
    @ResponseBody
    public Object getUserInfo(String openid) throws Exception {
       Object resultJson = wchatUserService.getUserInfo(openid);
       return resultJson;
    }

}

package com.controller;

import com.service.WchatCustomerMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/9/30.
 */
@Controller
@RequestMapping("wchat/custom_message")
public class WchatCustomerMessageController {

    @Autowired
    private WchatCustomerMessageService wchatCustomerMessageService;

    @RequestMapping("send")
    @ResponseBody
    public Object sendMsg(String openID,String content) throws Exception {
        Object resultJson = wchatCustomerMessageService.sendTextMessage(openID,content);
        return  resultJson;
    }
}

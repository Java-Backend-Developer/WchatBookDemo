package com.service;

/**
 * Created by Administrator on 2017/9/30.
 */
public interface WchatCustomerMessageService {
   Object  sendTextMessage(String openid, String content) throws Exception;
}

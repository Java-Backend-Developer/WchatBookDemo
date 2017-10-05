package com.service;

/**
 * Created by Administrator on 2017/9/30.
 */
public interface WchatUserService {
    Object getUserList() throws Exception;
    Object getUserInfo(String openid) throws Exception;
}

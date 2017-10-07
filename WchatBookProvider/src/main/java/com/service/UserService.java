package com.service;

import com.entity.User;

/**
 * Created by Administrator on 2017/10/6.
 */
public interface UserService {
    User get(Long id);
    boolean add(User user);
    boolean update(User user);
    User queryUserByParam(String username, String pwd);
    boolean queryUserByParam(String username);
}

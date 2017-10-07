package com.dao;

import com.entity.User;

/**
 * Created by Administrator on 2017/10/6.
 */
public interface UserDAO extends IDAO {
    User get(Long id);
    User getUserByParam(String username,String pwd);
    boolean queryUserByParam(String username);
}

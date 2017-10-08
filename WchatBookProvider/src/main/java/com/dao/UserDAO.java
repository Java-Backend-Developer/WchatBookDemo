package com.dao;

import com.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/10/6.
 */
public interface UserDAO extends IDAO {
    User queryByParam(User user);
    List<User> pageByParam(User user);
    boolean checkUsername(String username);
}

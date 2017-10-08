package com.service;

import com.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/10/6.
 */
public interface UserService {
    boolean add(User user);
    boolean remove(User user);
    boolean update(User user);
    User queryByParam(User user);
    List<User> pageByParam(User user);
    boolean checkUsername(String username);
}

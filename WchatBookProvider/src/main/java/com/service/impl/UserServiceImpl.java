package com.service.impl;

import com.dao.UserDAO;
import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/10/6.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User get(Long id) {
        return userDAO.get(id);
    }

    @Override
    public boolean add(User user) {
        return userDAO.saveResultBoolean(user);
    }

    @Override
    public boolean update(User user) {
        return userDAO.update(user)==1?true:false;
    }

    @Override
    public User queryUserByParam(String username, String pwd) {
        return userDAO.getUserByParam(username, pwd);
    }

    @Override
    public boolean queryUserByParam(String username) {
        return userDAO.queryUserByParam(username);
    }
}

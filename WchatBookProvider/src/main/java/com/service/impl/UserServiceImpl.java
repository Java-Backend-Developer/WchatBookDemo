package com.service.impl;

import com.dao.UserDAO;
import com.entity.User;
import com.entity.UserInfo;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/10/6.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean add(User user) {
        return userDAO.saveResultBoolean(user);
    }

    @Override
    public boolean remove(User user) {
        user = userDAO.queryByParam(user);
        return userDAO.remove(user.getId());
    }

    @Override
    public boolean update(User user) {
       User u = userDAO.queryByParam(user);
       if(user!=null){
           if(user.getUsername()!=null){
               u.setUsername(user.getUsername());
           }
           if(user.getPassword()!=null){
               u.setPassword(user.getPassword());
           }
           if(user.getEmail()!=null){
               u.setEmail(user.getEmail());
           }
           if(user.getMobile()!=null){
               u.setMobile(user.getMobile());
           }
           if(user.getType()!=null){
               u.setType(user.getType());
           }
           if(user.getUserInfo()!=null){
               UserInfo userInfo = u.getUserInfo();
               if(user.getUserInfo().getRealName()!=null){
                   userInfo.setRealName(user.getUserInfo().getRealName());
               }
               if(user.getUserInfo().getType()!=null){
                   userInfo.setType(user.getUserInfo().getType());
               }
               if(user.getUserInfo().getSex()!=null){
                   userInfo.setSex(user.getUserInfo().getSex());
               }
               u.setUserInfo(userInfo);
           }
       }

        return userDAO.update(u);
    }

    @Override
    public User queryByParam(User user) {
        return userDAO.queryByParam(user);
    }

    @Override
    public List<User> pageByParam(User user) {
        return userDAO.pageByParam(user);
    }

    @Override
    public boolean checkUsername(String username) {
        return userDAO.checkUsername(username);
    }

}

package com.dao.impl;

import com.dao.UserDAO;
import com.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/6.
 */
@Component("userDAO")
public class UserDAOImpl extends AbstractDAOHibernateImpl implements UserDAO {
    @Override
    protected Class getEntityClass() {
        return User.class;
    }

    @Override
    public User get(Long id){
        return (User) super.get(id);
    }

    @Override
    public User getUserByParam(String username, String pwd) {
        StringBuffer hql = new StringBuffer();
        hql.append("from User u  where u.username = ?  and  u.password = ? and u.invalid = ? ");
        List params = new ArrayList();
        params.add(username);
        params.add(pwd);
        params.add(true);
        return (User) super.queryByParam(hql.toString(),params);
    }

    @Override
    public boolean queryUserByParam(String username) {
        StringBuffer hql = new StringBuffer();
        hql.append("from User u  where u.username = ?  and u.invalid = ? ");
        List params = new ArrayList();
        params.add(username);
        params.add(true);
        User user = (User) super.queryByParam(hql.toString(),params);
        return user!=null?true:false;
    }


}

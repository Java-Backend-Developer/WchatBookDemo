package com.dao.impl;

import com.dao.UserDAO;
import com.entity.User;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
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
    public User queryByParam(User user) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        hql.append(" from User u where u.invalid = ? ");
        params.add(true);
        if (user.getId() != null) {
            hql.append(" and u.id = ? ");
            params.add(user.getId());
        } else {
            if (user.getUsername() != null) {
                hql.append(" and u.username = ? ");
                params.add(user.getUsername());
            }
            if (user.getEmail() != null) {
                hql.append(" and u.email = ? ");
                params.add(user.getEmail());
            }
            if (user.getMobile() != null) {
                hql.append(" and u.mobile = ? ");
                params.add(user.getMobile());
            }
            if (user.getUserInfo() != null) {
                if (user.getUserInfo().getId() != null) {
                    hql.append(" and u.userInfo.id = ? ");
                    params.add(user.getUserInfo().getId());
                }
                if (user.getUserInfo().getRealName() != null) {
                    hql.append(" and u.userInfo.realName = ? ");
                    params.add(user.getUserInfo().getRealName());
                }
            }
        }

        return (User) super.queryByParam(hql.toString(), params);
    }

    @Override
    public List<User> pageByParam(User user) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        hql.append(" from User u where u.invalid = ? ");
        params.add(true);
        if (user != null) {
            if (user.getId() != null) {
                hql.append(" and u.id = ? ");
                params.add(user.getId());
            }
            if (user.getUsername() != null) {
                hql.append(" and u.username like ? ");
                params.add("%" + user.getUsername() + "%");
            }
            if (user.getEmail() != null) {
                hql.append(" and u.email like ? ");
                params.add("%" + user.getEmail() + "%");
            }
            if (user.getMobile() != null) {
                hql.append(" and u.mobile like ? ");
                params.add("%" + user.getMobile() + "%");
            }
            if (user.getUserInfo() != null) {
                if (user.getUserInfo().getId() != null) {
                    hql.append(" and u.userInfo.id = ? ");
                    params.add(user.getUserInfo().getId());
                }
                if (user.getUserInfo().getRealName() != null) {
                    hql.append(" and u.userInfo.realName like ? ");
                    params.add("%" + user.getUserInfo().getRealName() + "%");
                }
                if (user.getUserInfo().getSex() != null) {
                    hql.append(" and u.userInfo.sex = ? ");
                    params.add(user.getUserInfo().getSex());
                }
            }
            if (user.getRole() != null) {
                if (user.getRole().getId() != null) {
                    hql.append(" and u.role.id = ? ");
                    params.add(user.getRole().getId());
                }
                if (user.getRole().getCode() != null) {
                    hql.append(" and u.role.code like ? ");
                    params.add("%" + user.getRole().getCode() + "%");
                }
                if (user.getRole().getName() != null) {
                    hql.append(" and u.role.name like ? ");
                    params.add("%" + user.getRole().getName() + "%");
                }
                if (user.getRole().getType() != null) {
                    hql.append(" and u.role.type = ? ");
                    params.add(user.getRole().getType());
                }
                if (user.getRole().getParentId() != null) {
                    hql.append(" and u.role.parentId = ? ");
                    params.add(user.getRole().getParentId());
                }
            }
        } else {
            user = new User();
        }
        return (List<User>) super.findByPageHql(hql.toString(), null, params, user, false);
    }

    @Override
    public boolean checkUsername(String username) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        hql.append(" from User u where u.invalid = ? and u.username = ? ");
        params.add(true);
        params.add(username);
        return super.queryByParam(hql.toString(), params) != null ? true : false;
    }


}

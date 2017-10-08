package com.dao.impl;

import com.dao.PermissionDAO;
import com.entity.Permission;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
@Component("permissionDAO")
public class PermissionDAOImpl extends AbstractDAOHibernateImpl implements PermissionDAO {


    @Override
    protected Class getEntityClass() {
        return Permission.class;
    }

    @Override
    public Permission queryByParam(Permission permission) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        hql.append(" from Permission p where p.invalid = ?");
        params.add(true);
        if (permission.getId() != null) {
            hql.append(" and p.id = ?");
            params.add(permission.getId());
        } else {
            if (permission.getCode() != null) {
                hql.append(" and p.code = ?");
                params.add(permission.getCode());
            }
            if (permission.getName() != null) {
                hql.append(" and p.name = ?");
                params.add(permission.getName());
            }
            if (permission.getStatus() != null) {
                hql.append(" and p.status = ?");
                params.add(permission.getStatus());
            }
        }
        return (Permission) super.queryByParam(hql.toString(), params);
    }

    @Override
    public List<Permission> pageByParam(Permission permission) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        hql.append(" from Permission p where p.invalid = ?");
        params.add(true);
        if (permission.getId() != null) {
            hql.append(" and p.id = ?");
            params.add(permission.getId());
        }
        if (permission.getCode() != null) {
            hql.append(" and p.code like ?");
            params.add("%" + permission.getCode() + "%");
        }
        if (permission.getName() != null) {
            hql.append(" and p.name like ?");
            params.add("%" + permission.getName() + "%");
        }
        if (permission.getStatus() != null) {
            hql.append(" and p.status = ?");
            params.add(permission.getStatus());
        }
        return super.findByPageHql(hql.toString(), null, params, permission, false);
    }

}

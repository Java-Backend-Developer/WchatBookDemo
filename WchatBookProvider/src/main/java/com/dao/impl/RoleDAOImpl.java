package com.dao.impl;

import com.dao.RoleDAO;
import com.entity.Permission;
import com.entity.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
@Component("roleDAO")
public class RoleDAOImpl extends AbstractDAOHibernateImpl implements RoleDAO {


    @Override
    protected Class getEntityClass() {
        return Role.class;
    }

    @Override
    public Role queryByParam(Role role) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        hql.append(" from Role r where r.invalid = ?");
        params.add(true);
        if (role.getId() != null) {
            hql.append(" and r.id = ?");
            params.add(role.getId());
        } else {
            if (role.getCode() != null) {
                hql.append(" and r.code = ?");
                params.add(role.getCode());
            }
            if (role.getName() != null) {
                hql.append(" and r.name = ?");
                params.add(role.getName());
            }
            if (role.getStatus() != null) {
                hql.append(" and r.status = ?");
                params.add(role.getStatus());
            }
        }
        return (Role) super.queryByParam(hql.toString(), params);
    }

    @Override
    public List<Role> pageByParam(Role role) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        hql.append(" from Role r where r.invalid = ?");
        params.add(true);
        if (role.getId() != null) {
            hql.append(" and r.id = ?");
            params.add(role.getId());
        }
        if (role.getCode() != null) {
            hql.append(" and r.code like ?");
            params.add("%"+role.getCode()+"%");
        }
        if (role.getName() != null) {
            hql.append(" and r.name like ?");
            params.add("%"+role.getName()+"%");
        }
        if (role.getDescription() != null) {
            hql.append(" and r.description like ?");
            params.add("%"+role.getDescription()+"%");
        }
        if (role.getType() != null) {
            hql.append(" and r.type = ?");
            params.add(role.getType());
        }
        if (role.getParentId() != null) {
            hql.append(" and r.parentId = ?");
            params.add(role.getParentId());
        }
        if (role.getPermission() != null&&role.getPermission().size()>0) {
            for (Permission permission : role.getPermission()) {
                hql.append(" and r.permission.id = ?");
                params.add(permission.getId());
            }
        }
        if (role.getStatus() != null) {
            hql.append(" and r.status = ?");
            params.add(role.getStatus());
        }

        return super.findByPageHql(hql.toString(),null,params,role,false);
    }

}

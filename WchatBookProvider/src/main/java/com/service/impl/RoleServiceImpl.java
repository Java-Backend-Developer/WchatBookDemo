package com.service.impl;

import com.dao.RoleDAO;
import com.entity.Role;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public boolean add(Role role) {
        return roleDAO.saveResultBoolean(role);
    }

    @Override
    public boolean remove(Role role) {
        role = roleDAO.queryByParam(role);
        return roleDAO.remove(role.getId());
    }

    @Override
    public boolean update(Role role) {
        Role r = roleDAO.queryByParam(role);
        if(role.getCode()!=null){
            r.setCode(role.getCode());
        }
        if(role.getName()!=null){
            r.setName(role.getName());
        }
        if(role.getDescription()!=null){
            r.setDescription(role.getDescription());
        }
        if(role.getType()!=null){
            r.setType(role.getType());
        }
        if(role.getDescription()!=null){
            r.setDescription(role.getDescription());
        }
        if(role.getPermission()!=null&&role.getPermission().size()>0){
            r.setPermission(role.getPermission());
        }
        if(role.getParentId()!=null){
            r.setParentId(role.getParentId());
        }
        if(role.getIsLeaf()!=null){
            r.setIsLeaf(role.getIsLeaf());
        }
        if(role.getStatus()!=null){
            r.setStatus(role.getStatus());
        }
        return roleDAO.update(r);
    }

    @Override
    public Role queryByParam(Role role) {
        return roleDAO.queryByParam(role);
    }

    @Override
    public List<Role> pageByParam(Role role) {
        return roleDAO.pageByParam(role);
    }
}

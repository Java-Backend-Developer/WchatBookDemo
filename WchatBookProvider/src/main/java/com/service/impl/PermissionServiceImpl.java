package com.service.impl;

import com.dao.PermissionDAO;
import com.entity.Permission;
import com.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionDAO permissionDAO;

    @Override
    public boolean add(Permission permission) {
        return permissionDAO.saveResultBoolean(permission);
    }

    @Override
    public boolean remove(Permission permission) {
        permission = permissionDAO.queryByParam(permission);
        return permissionDAO.remove(permission.getId());
    }

    @Override
    public boolean update(Permission permission) {
        Permission p = permissionDAO.queryByParam(permission);
        if(permission.getStatus()!=null){
            p.setStatus(permission.getStatus());
        }
        if(permission.getCode()!=null){
            p.setCode(permission.getCode());
        }
        if(permission.getName()!=null){
            p.setName(permission.getName());
        }
        if(permission.getDescription()!=null){
            p.setDescription(permission.getDescription());
        }

        return permissionDAO.update(p);
    }

    @Override
    public Permission queryByParam(Permission permission) {
        return permissionDAO.queryByParam(permission);
    }

    @Override
    public List<Permission> pageByParam(Permission permission) {
        return permissionDAO.pageByParam(permission);
    }
}

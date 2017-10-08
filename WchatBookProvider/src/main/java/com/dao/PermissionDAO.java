package com.dao;

import com.entity.Permission;

import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
public interface PermissionDAO extends IDAO {
    Permission queryByParam(Permission permission);
    List<Permission> pageByParam(Permission permission);
}

package com.service;

import com.entity.Permission;

import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
public interface PermissionService {
    boolean add(Permission permission);
    boolean remove(Permission permission);
    boolean update(Permission permission);
    Permission queryByParam(Permission permission);
    List<Permission> pageByParam(Permission permission);
}

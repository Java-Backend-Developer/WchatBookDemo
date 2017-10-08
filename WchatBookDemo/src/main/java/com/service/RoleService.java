package com.service;

import com.entity.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
public interface RoleService {
    boolean add(Role role);
    boolean remove(Role role);
    boolean update(Role role);
    Role queryByParam(Role role);
    List<Role> pageByParam(Role role);
}

package com.dao;

import com.entity.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
public interface RoleDAO extends IDAO {
    Role queryByParam(Role role);
    List<Role> pageByParam(Role role);
}

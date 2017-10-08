package com.controller;

import com.entity.Role;
import com.response.JsonResult;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
@RestController
@RequestMapping("role")
public class RoleJsonController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("add")
    public JsonResult add(Role role) {
        boolean resulet = roleService.add(role);
        if (resulet)
            return new JsonResult("0", "SUCCESS", null);
        return new JsonResult("-1", "FAIL", null);
    }

    @RequestMapping("remove")
    public JsonResult remove(Role role) {
        boolean resulet = roleService.remove(role);
        if (resulet)
            return new JsonResult("0", "SUCCESS", null);
        return new JsonResult("-1", "FAIL", null);
    }

    @RequestMapping("update")
    public JsonResult update(Role role) {
        boolean resulet = roleService.update(role);
        if (resulet)
            return new JsonResult("0", "SUCCESS", null);
        return new JsonResult("-1", "FAIL", null);
    }

    @RequestMapping("find")
    public JsonResult findRole(Role role) {
        Role data = roleService.queryByParam(role);
        return new JsonResult("0", "SUCCESS", data);
    }
    @RequestMapping("list")
    public JsonResult listRole(Role role) {
        List<Role> data = roleService.pageByParam(role);
        return new JsonResult("0", "SUCCESS", data);
    }
}

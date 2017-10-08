package com.controller;

import com.entity.Permission;
import com.response.JsonResult;
import com.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
@RestController
@RequestMapping("permission")
public class PermissionJsonController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("add")
    public JsonResult add(Permission permission) {
        boolean resulet = permissionService.add(permission);
        if (resulet)
            return new JsonResult("0", "SUCCESS", null);
        return new JsonResult("-1", "FAIL", null);
    }

    @RequestMapping("remove")
    public JsonResult remove(Permission permission) {
        boolean resulet = permissionService.remove(permission);
        if (resulet)
            return new JsonResult("0", "SUCCESS", null);
        return new JsonResult("-1", "FAIL", null);
    }

    @RequestMapping("update")
    public JsonResult update(Permission permission) {
        boolean resulet = permissionService.update(permission);
        if (resulet)
            return new JsonResult("0", "SUCCESS", null);
        return new JsonResult("-1", "FAIL", null);
    }

    @RequestMapping("find")
    public JsonResult find(Permission permission) {
        Permission data = permissionService.queryByParam(permission);
        return new JsonResult("0", "SUCCESS", data);
    }
    @RequestMapping("list")
    public JsonResult list(Permission permission) {
        List<Permission> data = permissionService.pageByParam(permission);
        return new JsonResult("0", "SUCCESS", data);
    }
}

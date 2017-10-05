package com.controller;

import com.service.WchatGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by Administrator on 2017/9/30.
 */
@Controller
@RequestMapping("wchat/group")
public class WchatGroupController {

    @Autowired
    private WchatGroupService wchatGroupService;

    /**
     * 获取分组列表
     * @return
     * @throws Exception
     */
    @RequestMapping("get")
    @ResponseBody
    public Object get() throws Exception {
      Object resultJson = wchatGroupService.get();
       return resultJson;
    }

    /**
     * 创建用户分组
     * @param name
     * @return
     * @throws Exception
     */
    @RequestMapping("create")
    @ResponseBody
    public Object create(String name) throws Exception {
      Object resultJson = wchatGroupService.create(name);
       return resultJson;
    }

    /**
     * 修改用户分组
     * @param id
     * @param name
     * @return
     * @throws Exception
     */
    @RequestMapping("update")
    @ResponseBody
    public Object update(int id,String name) throws Exception {
      Object resultJson = wchatGroupService.update(id,name);
       return resultJson;
    }

    /**
     * 查询用户所在分组
     * @param openID
     * @return
     * @throws Exception
     */
    @RequestMapping("getid")
    @ResponseBody
    public Object getId(String openID) throws Exception {
       Object resultJson = wchatGroupService.getId(openID);
       return resultJson;
    }


    /**
     * 移动用户分组
     * @param openID
     * @return
     * @throws Exception
     */
    @RequestMapping("update_members")
    @ResponseBody
    public Object updateMembers(String openID,int groupID) throws Exception {
       Object resultJson = wchatGroupService.updateMembers(openID,groupID);
       return resultJson;
    }
}

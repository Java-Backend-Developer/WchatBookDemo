package com.controller;

import com.entity.Label;
import com.response.JsonResult;
import com.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
@RestController
@RequestMapping("label")
public class LabelJsonController {

    @Autowired
    private LabelService labelService;

    @RequestMapping("add")
    public JsonResult add(Label label) {
        boolean resulet = labelService.add(label);
        if (resulet)
            return new JsonResult("0", "SUCCESS", null);
        return new JsonResult("-1", "FAIL", null);
    }

    @RequestMapping("remove")
    public JsonResult remove(Label label) {
        boolean resulet = labelService.remove(label);
        if (resulet)
            return new JsonResult("0", "SUCCESS", null);
        return new JsonResult("-1", "FAIL", null);
    }

    @RequestMapping("update")
    public JsonResult update(Label label) {
        boolean resulet = labelService.update(label);
        if (resulet)
            return new JsonResult("0", "SUCCESS", null);
        return new JsonResult("-1", "FAIL", null);
    }

    @RequestMapping("find")
    public JsonResult find(Label label) {
        Label data = labelService.queryByParam(label);
        return new JsonResult("0", "SUCCESS", data);
    }
    @RequestMapping("list")
    public JsonResult list(Label label) {
        List<Label> data = labelService.pageByParam(label);
        return new JsonResult("0", "SUCCESS", data);
    }
}

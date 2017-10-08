package com.controller;

import com.entity.Pet;
import com.response.JsonResult;
import com.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
@RestController
@RequestMapping("pet")
public class PetJsonController {

    @Autowired
    private PetService petService;

    @RequestMapping("add")
    public JsonResult add(Pet pet) {
        boolean resulet = petService.add(pet);
        if (resulet)
            return new JsonResult("0", "SUCCESS", null);
        return new JsonResult("-1", "FAIL", null);
    }

    @RequestMapping("remove")
    public JsonResult remove(Pet pet) {
        boolean resulet = petService.remove(pet);
        if (resulet)
            return new JsonResult("0", "SUCCESS", null);
        return new JsonResult("-1", "FAIL", null);
    }

    @RequestMapping("update")
    public JsonResult update(Pet pet) {
        boolean resulet = petService.update(pet);
        if (resulet)
            return new JsonResult("0", "SUCCESS", null);
        return new JsonResult("-1", "FAIL", null);
    }

    @RequestMapping("find")
    public JsonResult find(Pet pet) {
        Pet data = petService.queryByParam(pet);
        return new JsonResult("0", "SUCCESS", data);
    }

    @RequestMapping("list")
    public JsonResult list(Pet pet) {
        List<Pet> data = petService.pageByParam(pet);
        return new JsonResult("0", "SUCCESS", data);
    }
}

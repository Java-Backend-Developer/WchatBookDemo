package com.dao;

import com.entity.Pet;

import java.util.List;

/**
 * Created by Administrator on 2017/10/7.
 */
public interface PetDAO extends IDAO {
    Pet queryByParam(Pet pet);
    List<Pet> pageByParam(Pet pet);
}

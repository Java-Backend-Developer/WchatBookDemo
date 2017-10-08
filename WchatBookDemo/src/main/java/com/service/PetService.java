package com.service;

import com.entity.Pet;

import java.util.List;

/**
 * Created by Administrator on 2017/10/7.
 */
public interface PetService {
    boolean add(Pet pet);
    boolean remove(Pet pet);
    boolean update(Pet pet);
    Pet queryByParam(Pet pet);
    List<Pet> pageByParam(Pet pet);
}

package com.service.impl;

import com.dao.PetDAO;
import com.entity.Label;
import com.entity.Pet;
import com.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/10/7.
 */
@Service("petService")
@Transactional
public class PetServiceImpl implements PetService {

    @Autowired
    private PetDAO petDAO;


    @Override
    public boolean add(Pet pet) {
        return petDAO.saveResultBoolean(pet);
    }

    @Override
    public boolean remove(Pet pet) {
        pet = petDAO.queryByParam(pet);
        return petDAO.remove(pet.getId());
    }

    @Override
    public boolean update(Pet pet) {
        Pet p = petDAO.queryByParam(pet);
        if(pet.getType()!=null){
            p.setType(pet.getType());
        }
        if(pet.getName()!=null){
            p.setName(pet.getName());
        }
        if(pet.getDescription()!=null){
            p.setDescription(pet.getDescription());
        }
        if(pet.getLabels()!=null&&pet.getLabels().size()>0){
            p.setLabels(pet.getLabels());
        }
        if(pet.getPictures()!=null&&pet.getPictures().size()>0){
            p.setPictures(pet.getPictures());
        }
        return petDAO.update(p);
    }

    @Override
    public Pet queryByParam(Pet pet) {
        return petDAO.queryByParam(pet);
    }

    @Override
    public List<Pet> pageByParam(Pet pet) {
        return petDAO.pageByParam(pet);
    }
}

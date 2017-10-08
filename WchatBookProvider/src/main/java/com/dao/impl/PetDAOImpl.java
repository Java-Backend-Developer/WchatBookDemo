package com.dao.impl;

import com.dao.PetDAO;
import com.entity.Label;
import com.entity.Pet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/7.
 */
@Component("petDAO")
public class PetDAOImpl extends AbstractDAOHibernateImpl implements PetDAO {

    @Override
    protected Class getEntityClass() {
        return Pet.class;
    }


    @Override
    public Pet queryByParam(Pet pet) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        hql.append(" from Pet p where p.invalid = ? ");
        params.add(true);
        if (pet.getId() != null) {
            hql.append(" and p.id = ? ");
            params.add(pet.getId());
        } else {
            if (pet.getName() != null) {
                hql.append(" and p.name = ? ");
                params.add(pet.getName());
            }
        }
        return (Pet) super.queryByParam(hql.toString(), params);
    }

    @Override
    public List<Pet> pageByParam(Pet pet) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        hql.append(" from Pet p where p.invalid = ? ");
        params.add(true);
        if (pet != null) {
            if (pet.getId() != null) {
                hql.append(" and p.id = ? ");
                params.add(pet.getId());
            }
            if (pet.getName() != null) {
                hql.append(" and p.name like ? ");
                params.add("%" + pet.getName() + "%");
            }
            if (pet.getType() != null) {
                hql.append(" and p.type = ? ");
                params.add(pet.getType());
            }
            if (pet.getDescription() != null) {
                hql.append(" and p.description like ? ");
                params.add("%" + pet.getDescription() + "%");
            }
            if (pet.getLabels() != null && pet.getLabels().size() != 0) {
                for (Label label : pet.getLabels()) {
                    if (label.getId() != null) {
                        hql.append(" and p.labels.id = ? ");
                        params.add(label.getId());
                    }
                    if (label.getCode() != null) {
                        hql.append(" and p.labels.code like ? ");
                        params.add("%" + label.getCode() + "%");
                    }
                    if (label.getName() != null) {
                        hql.append(" and p.labels.name like ? ");
                        params.add("%" + label.getName() + "%");
                    }
                }

            }
        }
        return super.findByPageHql(hql.toString(), null, params, pet, false);
    }

}

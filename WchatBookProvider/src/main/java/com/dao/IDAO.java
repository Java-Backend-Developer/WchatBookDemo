package com.dao;

import com.entity.AbstractEntity;

import java.io.Serializable;

public interface IDAO {

     Serializable add(AbstractEntity abstractEntity);

     boolean update(AbstractEntity abstractEntity);

     boolean remove(Long id);

     int delete(Long id);

     void saveOrUpdate(AbstractEntity abstractEntity);

    boolean saveResultBoolean(AbstractEntity abstractEntity);
    
    boolean saveAppoint(AbstractEntity abstractEntity);
}

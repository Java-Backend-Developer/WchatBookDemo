package com.dao;

import com.entity.AbstractEntity;

public interface IDAO {

    public void add(AbstractEntity abstractEntity);

    public int update(AbstractEntity abstractEntity);

    public int remove(Long id);

    public int delete(Long id);

    public void saveOrUpdate(AbstractEntity abstractEntity);

    boolean saveResultBoolean(AbstractEntity abstractEntity);
    
    boolean saveAppoint(AbstractEntity abstractEntity);
}

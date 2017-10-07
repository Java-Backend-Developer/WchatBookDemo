package com.dao.impl;

import com.dao.TestDAO;
import com.entity.AbstractEntity;
import com.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;


/**
 * Created by Administrator on 2017/10/5.
 */
@Component("testDAOHibernateImpl")
public class TestDAOImpl extends AbstractDAOHibernateImpl implements TestDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    protected Class getEntityClass() {
        return Test.class;
    }

    @Override
    public AbstractEntity get(Long id) {
        return super.get(id);
    }
}

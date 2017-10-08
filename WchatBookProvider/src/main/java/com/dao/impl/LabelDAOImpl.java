package com.dao.impl;

import com.dao.LabelDAO;
import com.entity.Label;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/7.
 */
@Component("labelDAO")
public class LabelDAOImpl extends AbstractDAOHibernateImpl implements LabelDAO {
    @Override
    protected Class getEntityClass() {
        return Label.class;
    }


    @Override
    public Label queryByParam(Label label) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        hql.append(" from Label l where l.invalid = ? ");
        params.add(true);
        if(label.getId()!=null){
            hql.append("  and l.id = ? ");
            params.add(label.getId());
        } else  {
            if (label.getCode() != null) {
                hql.append("  and l.code = ? ");
                params.add(label.getCode());
            }
            if (label.getName() != null) {
                hql.append("  and l.name = ? ");
                params.add(label.getName());
            }
            if (label.getDescription() != null) {
                hql.append("  and l.description = ? ");
                params.add(label.getDescription());
            }
            if (label.getStatus() != null) {
                hql.append("  and l.status = ? ");
                params.add(label.getStatus());
            }
        }
        return (Label) super.queryByParam(hql.toString(), params);
    }

    @Override
    public List<Label> pageByParam(Label label) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        hql.append(" from Label l where l.invalid = ? ");
        params.add(true);
        if (label != null) {
            if (label.getId() != null) {
                hql.append("  and l.id = ? ");
                params.add(label.getId());
            }
            if (label.getCode() != null) {
                hql.append("  and l.code = ? ");
                params.add(label.getCode());
            }
            if (label.getName() != null) {
                hql.append("  and l.name = ? ");
                params.add(label.getName());
            }
            if (label.getDescription() != null) {
                hql.append("  and l.description = ? ");
                params.add(label.getDescription());
            }
            if (label.getStatus() != null) {
                hql.append("  and l.status = ? ");
                params.add(label.getStatus());
            }
        }
        return super.findByPageHql(hql.toString(), null, params, label, false);
    }
}

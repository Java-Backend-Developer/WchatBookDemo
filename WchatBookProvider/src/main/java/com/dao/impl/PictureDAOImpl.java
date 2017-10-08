package com.dao.impl;

import com.dao.PictureDAO;
import com.entity.Picture;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/8.
 */
@Component("pictureDAO")
public class PictureDAOImpl extends AbstractDAOHibernateImpl implements PictureDAO {

    @Override
    protected Class getEntityClass() {
        return Picture.class;
    }

    @Override
    public Picture queryByParam(Picture picture) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        hql.append(" from Picture p where p.invalid = ? and p.id = ?");
        params.add(true);
        params.add(picture.getId());
        return (Picture) super.queryByParam(hql.toString(),params);
    }

    @Override
    public List<Picture> pageByParam(Picture picture) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        hql.append(" from Picture p where p.invalid = ?");
        params.add(true);
        if(picture!=null){
            if(picture.getStatus()!=null){
                hql.append(" and p.status = ?");
                params.add(picture.getStatus());
            }
            if(picture.getWidth()!=null){
                hql.append(" and p.width = ?");
                params.add(picture.getWidth());
            }
            if(picture.getHeight()!=null){
                hql.append(" and p.height = ?");
                params.add(picture.getHeight());
            }
        }else {
            picture = new Picture();
        }
        return super.findByPageHql(hql.toString(),null,params,picture,false);
    }


}

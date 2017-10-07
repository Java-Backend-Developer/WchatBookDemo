package com.dao.impl;

import com.dao.MobileLoginInfoDAO;
import com.entity.MobileLoginInfo;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/6.
 */
@Component("mobileLoginInfoDAO")
public class MobileLoginInfoDAOImpl extends AbstractDAOHibernateImpl implements MobileLoginInfoDAO {
    @Override
    protected Class getEntityClass() {
        return MobileLoginInfo.class;
    }

    @Override
    public MobileLoginInfo get(Long id) {
        return (MobileLoginInfo) super.get(id);
    }

    @Override
    public MobileLoginInfo queryBySerial(String serial) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from MobileLoginInfo m  where m.serial = ?  and m.invalid = ?");
        List  params = new ArrayList();
        params.add(serial);
        params.add(true);
        return (MobileLoginInfo) super.queryByParam(hql.toString(),params);
    }

    @Override
    public boolean remove(MobileLoginInfo mobileLoginInfo) {
        StringBuffer hql = new StringBuffer("update MobileLoginInfo m set m.invalid=?,m.updatedDatetime=? where m.user.id=?  and m.invalid=?");
        List<Object> params = new ArrayList<Object>();
        params.add(false);
        params.add(new Timestamp(System.currentTimeMillis()));
        params.add(mobileLoginInfo.getUser().getId());
        params.add(true);
        return super.updateByParam(hql.toString(), params);
    }
}

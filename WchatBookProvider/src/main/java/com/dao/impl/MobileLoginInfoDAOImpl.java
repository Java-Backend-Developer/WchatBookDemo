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
    public boolean remove(MobileLoginInfo mobileLoginInfo) {
        StringBuffer hql = new StringBuffer();
        hql.append(" update MobileLoginInfo m  set m.invalid = ? where m.user.id = ?  and m.invalid = ?");
        List params = new ArrayList();
        params.add(false);
        params.add(mobileLoginInfo.getUser().getId());
        params.add(true);
        return super.updateByParam(hql.toString(), params);
    }


    @Override
    public MobileLoginInfo queryByParam(MobileLoginInfo mobileLoginInfo) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        hql.append(" from MobileLoginInfo m  where  and m.invalid = ?");
        params.add(true);
        if (mobileLoginInfo.getId() != null) {
            hql.append(" and m.id = ?");
            params.add(mobileLoginInfo.getId());
        } else {
            if (mobileLoginInfo.getAppToken() != null) {
                hql.append(" and m.appToken = ?");
                params.add(mobileLoginInfo.getAppToken());
            }
            if (mobileLoginInfo.getSerial() != null) {
                hql.append(" and m.serial = ?");
                params.add(mobileLoginInfo.getSerial());
            }
        }
        return (MobileLoginInfo) super.queryByParam(hql.toString(), params);
    }

    @Override
    public List<MobileLoginInfo> pageByParam(MobileLoginInfo mobileLoginInfo) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        hql.append(" from MobileLoginInfo m  where  and m.invalid = ?");
        params.add(true);
        if (mobileLoginInfo != null) {
            if (mobileLoginInfo.getAppToken() != null) {
                hql.append(" and m.appToken like ?");
                params.add("%" + mobileLoginInfo.getAppToken() + "%");
            }
            if (mobileLoginInfo.getSerial() != null) {
                hql.append(" and m.serial like ?");
                params.add("%" + mobileLoginInfo.getSerial() + "%");
            }
            if (mobileLoginInfo.getModel() != null) {
                hql.append(" and m.model like ?");
                params.add("%" + mobileLoginInfo.getModel() + "%");
            }
            if (mobileLoginInfo.getAppUserName() != null) {
                hql.append(" and m.appUserName like ?");
                params.add("%" + mobileLoginInfo.getAppUserName() + "%");
            }
            if (mobileLoginInfo.getVersionCode() != null) {
                hql.append(" and m.versionCode like ?");
                params.add("%" + mobileLoginInfo.getVersionCode() + "%");
            }
        }
        return super.findByPageHql(hql.toString(), null, params, mobileLoginInfo, false);
    }
}

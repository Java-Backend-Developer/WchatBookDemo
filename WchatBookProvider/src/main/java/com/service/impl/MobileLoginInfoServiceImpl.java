package com.service.impl;

import com.dao.MobileLoginInfoDAO;
import com.entity.MobileLoginInfo;
import com.service.MobileLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/10/6.
 */
@Service("mobileLoginInfoService")
@Transactional
public class MobileLoginInfoServiceImpl implements MobileLoginInfoService {

    @Autowired
    private MobileLoginInfoDAO mobileLoginInfoDAO;

    @Override
    public boolean add(MobileLoginInfo mobileLoginInfo) {
        return mobileLoginInfoDAO.saveResultBoolean(mobileLoginInfo);
    }

    @Override
    public boolean remove(MobileLoginInfo mobileLoginInfo) {
        return mobileLoginInfoDAO.remove(mobileLoginInfo);
    }

    @Override
    public MobileLoginInfo queryByParam(MobileLoginInfo mobileLoginInfo) {
        return mobileLoginInfoDAO.queryByParam(mobileLoginInfo);
    }

    @Override
    public List<MobileLoginInfo> pageByParam(MobileLoginInfo mobileLoginInfo) {
        return mobileLoginInfoDAO.pageByParam(mobileLoginInfo);
    }

}

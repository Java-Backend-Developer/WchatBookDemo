package com.dao;

import com.entity.MobileLoginInfo;

/**
 * Created by Administrator on 2017/10/6.
 */
public interface MobileLoginInfoDAO extends IDAO {
    MobileLoginInfo get(Long id);
    MobileLoginInfo queryBySerial(String serial);
    boolean remove(MobileLoginInfo mobileLoginInfo);
}

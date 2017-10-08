package com.dao;

import com.entity.MobileLoginInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/10/6.
 */
public interface MobileLoginInfoDAO extends IDAO {
    boolean remove(MobileLoginInfo mobileLoginInfo);
    MobileLoginInfo queryByParam(MobileLoginInfo mobileLoginInfo);
    List<MobileLoginInfo> pageByParam(MobileLoginInfo mobileLoginInfo);
}

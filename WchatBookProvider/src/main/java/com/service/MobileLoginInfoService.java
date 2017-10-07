package com.service;

import com.entity.MobileLoginInfo;

/**
 * Created by Administrator on 2017/10/6.
 */
public interface MobileLoginInfoService {
    boolean add(MobileLoginInfo mobileLoginInfo);
    MobileLoginInfo get(Long id);
    boolean remove(MobileLoginInfo mobileLoginInfo);
    MobileLoginInfo queryBySerial(String serial);
}

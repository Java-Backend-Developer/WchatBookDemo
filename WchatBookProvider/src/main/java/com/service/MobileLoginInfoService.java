package com.service;

import com.entity.MobileLoginInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/10/6.
 */
public interface MobileLoginInfoService {
    boolean add(MobileLoginInfo mobileLoginInfo);
    boolean remove(MobileLoginInfo mobileLoginInfo);
    MobileLoginInfo queryByParam(MobileLoginInfo mobileLoginInfo);
    List<MobileLoginInfo> pageByParam(MobileLoginInfo mobileLoginInfo);
}

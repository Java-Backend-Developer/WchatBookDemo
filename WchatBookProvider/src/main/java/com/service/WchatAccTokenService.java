package com.service;

import com.constants.WchatAccToken;

/**
 * Created by Administrator on 2017/9/30.
 */
public interface WchatAccTokenService {
    WchatAccToken getAccess_Token() throws Exception;
}

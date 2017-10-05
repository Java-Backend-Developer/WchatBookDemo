package com.service;

import java.io.File;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/1.
 */
public interface WchatMediaService {
    Object mediaUpload(int type, File file, String title, String introduction) throws Exception;
    Map getMedia(int type, String mediaId) throws Exception;
}

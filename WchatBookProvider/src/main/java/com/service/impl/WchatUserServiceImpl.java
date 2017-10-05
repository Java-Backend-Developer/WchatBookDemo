package com.service.impl;

import com.constants.WchatAccToken;
import com.helper.GlobEnv;
import com.service.WchatAccTokenService;
import com.service.WchatUserService;
import com.util.HttpClientImpl;
import net.sf.json.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/30.
 */
@Service("wchatUserService")
public class WchatUserServiceImpl implements WchatUserService {

    @Autowired
    private WchatAccTokenService wchatAccTokenService;

    public Object getUserList() throws Exception {
        String url = GlobEnv.get("wchat.url")+GlobEnv.get("wchat.user.get.method")+"?access_token="+wchatAccTokenService.getAccess_Token().getAccess_token();
        String result = null;
        try {
            HttpClientImpl client = new HttpClientImpl(url);
            HttpGet httpGet = new HttpGet(url);
            client.execute(httpGet);
            result = client.getContentStr();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result==null||result.length()==0)
            throw new Exception("获取用户列表失败！");
        JSONObject resultJson = JSONObject.fromObject(result);
        return resultJson;
    }

    public Object getUserInfo(String openid) throws Exception {
        String url = GlobEnv.get("wchat.url")+GlobEnv.get("wchat.user.info.method")+"?access_token="+wchatAccTokenService.getAccess_Token().getAccess_token()
                +"&openid="+openid;
        String result = null;
        try {
            HttpClientImpl client = new HttpClientImpl(url);
            HttpGet httpGet = new HttpGet(url);
            client.execute(httpGet);
            result = client.getContentStr();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result==null||result.length()==0)
            throw new Exception("获取用户信息失败！");
        JSONObject resultJson = JSONObject.fromObject(result);
        return resultJson;
    }
}

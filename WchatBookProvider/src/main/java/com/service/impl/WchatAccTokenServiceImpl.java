package com.service.impl;

import com.constants.WchatAccToken;
import com.helper.GlobEnv;
import com.service.WchatAccTokenService;
import com.util.DateUtil;
import com.util.HttpClientImpl;
import net.sf.json.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/30.
 */
@Service("wchatAccTokenService")
@Transactional
public class WchatAccTokenServiceImpl implements WchatAccTokenService {

    @Autowired
    private WchatAccToken token;

    public WchatAccToken getAccess_Token() throws Exception {
        if(token!=null&&token.getAccess_token()!=null&&token.getDate()!=null&& DateUtil.hoursBetween(token.getDate(),new Date(System.currentTimeMillis()))<2)
            return token;
        String url = new String((GlobEnv.get("wchat.url")+GlobEnv.get("wchat.accToken.method")+"?grant_type="
                +GlobEnv.get("wchat.grant_type")+"&appid="+GlobEnv.get("wchat.appID")+"&secret="+GlobEnv.get("wchat.appsecret")).getBytes(),"utf-8");
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
            throw new Exception("获取accToken失败！");
        JSONObject resultJson = JSONObject.fromObject(result);
        String accToken = resultJson.get("access_token").toString();
        int num = token.getNum();
        num+=1;
        token.setNum(num);
        token.setAccess_token(accToken);
        token.setDate(new Date(System.currentTimeMillis()));
        return token;
    }
}

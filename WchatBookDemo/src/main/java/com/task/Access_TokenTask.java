package com.task;

import com.constants.WchatAccToken;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/29.
 */
@Component
public class Access_TokenTask {

   /* @Autowired
    private WchatAccToken token;*/
  //  @Scheduled(cron="0 0 * * * ? ")   //每天每小时执行一次
    public void getAccess_Token() throws Exception {
       /* String url = GlobEnv.get("wchat.url")+ "/"+GlobEnv.get("wchat.accToken.method")+"?grant_type="
                +GlobEnv.get("wchat.grant_type")+"&appid="+GlobEnv.get("wchat.appID")+"&appsecret="+GlobEnv.get("wchat.appsecret");
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
        token.setDate(new Date(System.currentTimeMillis()));*/
    }
}

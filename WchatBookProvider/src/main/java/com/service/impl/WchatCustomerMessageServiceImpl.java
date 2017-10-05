package com.service.impl;

import com.constants.WchatAccToken;
import com.helper.GlobEnv;
import com.service.WchatAccTokenService;
import com.service.WchatCustomerMessageService;
import com.util.HttpClientImpl;
import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */
@Service("wchatCustomerMessageService")
public class WchatCustomerMessageServiceImpl implements WchatCustomerMessageService {

    @Autowired
    private WchatAccTokenService wchatAccTokenService;

    public Object sendTextMessage(String openid, String content) throws Exception {
        String url = GlobEnv.get("wchat.url")+GlobEnv.get("wchat.sendmsg.method")+"?access_token="+wchatAccTokenService.getAccess_Token().getAccess_token();
        JSONObject contentJson = new JSONObject();
        contentJson.put("content",content);
        JSONObject textJson = new JSONObject();
        textJson.put("touser",openid);
        textJson.put("msgtype","text");
        textJson.put("text",contentJson);
        String result = null;
        try {
            HttpClientImpl client = new HttpClientImpl(url);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(textJson.toString(),"utf-8"));
            client.execute(httpPost);
            result = client.getContentStr();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result==null||result.trim().equals(""))
            throw new Exception("发送客服消息失败！");
        JSONObject resultJson = JSONObject.fromObject(result);
        return resultJson;
    }
}

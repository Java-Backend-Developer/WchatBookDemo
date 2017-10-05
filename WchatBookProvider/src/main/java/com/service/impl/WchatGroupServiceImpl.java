package com.service.impl;

import com.constants.WchatAccToken;
import com.helper.GlobEnv;
import com.service.WchatAccTokenService;
import com.service.WchatGroupService;
import com.util.HttpClientImpl;
import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
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
@Service("wchatGroupService")
public class WchatGroupServiceImpl implements WchatGroupService {

    @Autowired
    private WchatAccTokenService wchatAccTokenService;

    public Object get() throws Exception {
        String url = GlobEnv.get("wchat.url")+GlobEnv.get("wchat.group.get.method")+"?access_token="+wchatAccTokenService.getAccess_Token().getAccess_token();
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
            throw new Exception("获取分组列表失败！");
        JSONObject resultJson = JSONObject.fromObject(result);
        return resultJson;
    }

    public Object create(String name) throws Exception {
        String url = GlobEnv.get("wchat.url")+GlobEnv.get("wchat.group.create.method")+"?access_token="+wchatAccTokenService.getAccess_Token().getAccess_token();
        String result = null;
        JSONObject nameJson = new JSONObject();
        nameJson.put("name",name);
        JSONObject groupJson = new JSONObject();
        groupJson.put("group",nameJson);
        try {
            HttpClientImpl client = new HttpClientImpl(url);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(groupJson.toString(),"utf-8"));
            client.execute(httpPost);
            result = client.getContentStr();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result==null||result.length()==0)
            throw new Exception("创建用户分组失败！");
        JSONObject resultJson = JSONObject.fromObject(result);
        return resultJson;
    }

    public Object update(int id, String name) throws Exception {
        String url = GlobEnv.get("wchat.url")+GlobEnv.get("wchat.group.update.method")+"?access_token="+wchatAccTokenService.getAccess_Token().getAccess_token();
        String result = null;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("name",name);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("group",jsonObject);
        try {
            HttpClientImpl client = new HttpClientImpl(url);
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("body", jsonObject2.toString()));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            client.execute(httpPost);
            result = client.getContentStr();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result==null||result.length()==0)
            throw new Exception("修改用户分组失败！");
        JSONObject resultJson = JSONObject.fromObject(result);
        return resultJson;
    }

    public Object getId(String openID) throws Exception {
        String url = GlobEnv.get("wchat.url")+GlobEnv.get("wchat.group.getId.method")+"?access_token="+wchatAccTokenService.getAccess_Token().getAccess_token();
        String result = null;;
        try {
            HttpClientImpl client = new HttpClientImpl(url);
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("openid", openID));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            client.execute(httpPost);
            result = client.getContentStr();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result==null||result.length()==0)
            throw new Exception("查询用户所在分组失败！");
        JSONObject resultJson = JSONObject.fromObject(result);
        return resultJson;
    }

    public Object updateMembers(String openID, int groupID) throws Exception {
        String url = GlobEnv.get("wchat.url")+GlobEnv.get("wchat.group.updateMembers.method")+"?access_token="+wchatAccTokenService.getAccess_Token().getAccess_token();
        String result = null;;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openid",openID);
        jsonObject.put("to_groupid",groupID);
        try {
            HttpClientImpl client = new HttpClientImpl(url);
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("body", jsonObject.toString()));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            client.execute(httpPost);
            result = client.getContentStr();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result==null||result.length()==0)
            throw new Exception("移动用户分组失败！");
        JSONObject resultJson = JSONObject.fromObject(result);
        return resultJson;
    }
}

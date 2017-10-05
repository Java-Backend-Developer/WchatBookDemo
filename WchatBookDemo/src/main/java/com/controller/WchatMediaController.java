package com.controller;

import com.service.WchatMediaService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/1.
 */
@Controller
@RequestMapping("wchat/media")
public class WchatMediaController {

    @Autowired
    private WchatMediaService wchatMediaService;

    @RequestMapping("upload")
    @ResponseBody
    public Object upload(int type, String title, String introduction) throws Exception {

        File file = new File("C:\\Users\\Administrator\\Desktop\\sfx.mp3");
        Object resultJson = wchatMediaService.mediaUpload(type,file,title,introduction);
        return resultJson;
    }

    @RequestMapping("get")
    @ResponseBody
    public ResponseEntity<byte[]> get(int type, String mediaId) throws Exception {

        Map<String,Object> map = wchatMediaService.getMedia(type,mediaId);

        File file = (File) map.get("file");
        HttpHeaders headers = (HttpHeaders) map.get("head");

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
    }
}

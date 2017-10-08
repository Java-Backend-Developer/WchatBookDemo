package com.controller;

import com.entity.MobileLoginInfo;
import com.entity.Picture;
import com.entity.User;
import com.response.JsonResult;
import com.service.MobileLoginInfoService;
import com.service.PictureService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/10/8.
 */
@RestController
@RequestMapping("picture")
public class PictureJsonController {

    @Autowired
    private PictureService pictureService;
    @Autowired
    private MobileLoginInfoService mobileLoginInfoService;

    @RequestMapping("upload")
    public JsonResult upload(HttpServletRequest request, @RequestParam(value = "file") MultipartFile file,
                             @RequestParam(required = false, defaultValue = "20000000") int maxSize) throws IOException {


        String appToken = request.getHeader("appToken");
        MobileLoginInfo mobileLoginInfo = new MobileLoginInfo();
        mobileLoginInfo.setAppToken(appToken);
        mobileLoginInfo = mobileLoginInfoService.queryByParam(mobileLoginInfo);
        Long id = pictureService.upload(file,mobileLoginInfo.getUser());
        return new JsonResult("0","SUCCESS",id);
    }

    @RequestMapping("download")
    public ResponseEntity<byte[]> download(Picture picture) throws IOException {


        picture = pictureService.queryByParam(picture);
        File file = new File(picture.getPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(file.getName(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
    }
}

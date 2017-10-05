package com.controller;

import com.constants.WchatAccToken;
import com.service.WchatAccTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/9/30.
 */
@Controller
@RequestMapping("wchat/token")
public class WchatAccTokenController {

    @Autowired
    private WchatAccTokenService wchatAccTokenService;

    private String Token = "lfkj_xfcamp_token";

    /**
     * 验证服务器
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @param response
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "check")
    @ResponseBody
    public void test(String signature, String timestamp, String nonce, String echostr, HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
        // 将token、timestamp、nonce三个参数进行字典序排序
        System.out.println("signature:"+signature);
        System.out.println("timestamp:"+timestamp);
        System.out.println("nonce:"+nonce);
        System.out.println("echostr:"+echostr);
        System.out.println("TOKEN:"+Token);
        String[] params = new String[] { Token, timestamp, nonce };
        Arrays.sort(params);
        // 将三个参数字符串拼接成一个字符串进行sha1加密
        String clearText = params[0] + params[1] + params[2];
        String algorithm = "SHA-1";
        String sign = new String(
                org.apache.commons.codec.binary.Hex.encodeHex(MessageDigest.getInstance(algorithm).digest((clearText).getBytes()), true));
        // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if (signature.equals(sign)) {
            response.getWriter().print(echostr);
        }
        /*return "你好";*/
    }

    /**
     * 获取access_token
     * @return
     * @throws Exception
     */
    @RequestMapping("get_access_token")
    @ResponseBody
    public Object getAccess_Token() throws Exception {
     Object o = wchatAccTokenService.getAccess_Token();
      return o;
    }
}

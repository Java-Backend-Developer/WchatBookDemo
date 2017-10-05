package com.service.impl;

import com.enums.MediaEnums;
import com.helper.GlobEnv;
import com.service.WchatAccTokenService;
import com.service.WchatMediaService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/1.
 */
@Service("wchatMediaUploadService")
public class WchatMediaServiceImpl implements WchatMediaService {

    @Autowired
    private WchatAccTokenService wchatAccTokenService;


    /**
     * 上传多媒体文件（临时上传仅保存三天）
     *
     * @param type
     * @param file
     * @param title
     * @param introduction
     * @return
     * @throws Exception
     */
    public Object mediaUpload(int type, File file, String title, String introduction) throws Exception {

        String urlStr = null;
        JSONObject jsonObject = null;
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        if (type == 3) {
            //这块是用来处理如果上传的类型是video的类型的
            JSONObject videoJson = new JSONObject();
            videoJson.put("title", title);
            videoJson.put("introduction", introduction);
            urlStr = GlobEnv.get("wchat.url") + GlobEnv.get("wchat.media.upload.method") + "?access_token="
                    + wchatAccTokenService.getAccess_Token().getAccess_token() + "&type=" + MediaEnums.getValueByCode(type) + "/" + suffix
                    + "&description=" + videoJson.toString();
        } else {
            urlStr = GlobEnv.get("wchat.url") + GlobEnv.get("wchat.media.upload.method") + "?access_token="
                    + wchatAccTokenService.getAccess_Token().getAccess_token() + "&type=" + MediaEnums.getValueByCode(type) + "/" + suffix;
        }

        try {

            URL url = new URL(urlStr);
            String result = null;
            long filelength = file.length();
            /**
             *  你们需要在这里根据文件后缀suffix将type的值设置成对应的mime类型的值
             */
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false); // post方式不能使用缓存
            // 设置请求头信息
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");

            // 设置边界,这里的boundary是http协议里面的分割符，不懂的可以百度(http 协议 boundary)，这里boundary 可以是任意的值(111,2222)都行
            String BOUNDARY = "----------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            // 请求正文信息
            // 第一部分：

            StringBuilder sb = new StringBuilder();

            //这块是post提交type的值也就是文件对应的mime类型值
            sb.append("--"); // 必须多两道线 这里说明下，这两个横杠是http协议要求的，用来分隔提交的参数用的，不懂的可以看看http 协议头
            sb.append(BOUNDARY);
            sb.append("\r\n");
            //这里是media参数相关的信息，这里是否能分开下我没有试，感兴趣的可以试试
            sb.append("Content-Disposition: form-data;name=\"media\";filename=\""
                    + fileName + "\";filelength=\"" + filelength + "\" \r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            System.out.println(sb.toString());
            byte[] head = sb.toString().getBytes("utf-8");
            // 获得输出流
            OutputStream out = new DataOutputStream(con.getOutputStream());
            // 输出表头
            out.write(head);
            // 文件正文部分
            // 把文件已流文件的方式 推入到url中
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();
            // 结尾部分，这里结尾表示整体的参数的结尾，结尾要用"--"作为结束，这些都是http协议的规定
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
            out.write(foot);
            out.flush();
            out.close();
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = null;
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
            // 使用JSON-lib解析返回结果
            jsonObject = JSONObject.fromObject(result);
            if (jsonObject.has("media_id")) {
                System.out.println("media_id:" + jsonObject.getString("media_id"));
            } else {
                System.out.println(jsonObject.toString());
            }
            System.out.println("json:" + jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return jsonObject;
    }

    /**
     * 下载多媒体文件
     *
     * @param mediaId
     * @return
     * @throws Exception
     */
    public Map getMedia(int type, String mediaId) throws Exception {

        Map map = new HashMap();
        String urlStr = null;
        String filePath = null;
        // 拼接请求地址
        if (type == 3) {
            urlStr = GlobEnv.get("wchat.media.video.get.url") + "?access_token="
                    + wchatAccTokenService.getAccess_Token().getAccess_token() + "&media_id=" + mediaId;
        } else {
            urlStr = GlobEnv.get("wchat.url") + GlobEnv.get("wchat.media.get.method") + "?access_token="
                    + wchatAccTokenService.getAccess_Token().getAccess_token() + "&media_id=" + mediaId;
        }
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");

            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
       /*     byte[] buf = new byte[8096];
            while(bis.){

            }*/
            String savePath = GlobEnv.get("file.stor");
            if(type == 3){

            }else{

            }
            String contentDisposition = conn.getHeaderField("content-disposition");
            //     String[] fileNames = contentType.split(":");
            //    String fileName = fileNames[1];
            String fileName = "111111.mp4";

            filePath = savePath + fileName;

            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1)
                fos.write(buf, 0, size);
            fos.close();
            bis.close();
            conn.disconnect();
            String info = String.format("下载媒体文件成功，filePath=" + filePath);
            System.out.println(info);
        } catch (Exception e) {
            filePath = null;
            String error = String.format("下载媒体文件失败：%s", e);
            System.out.println(error);
        }

        return map;
    }

    public Object addmedia(int type, File file, String title, String introduction) throws Exception {
        String urlStr = GlobEnv.get("wchat.url") + GlobEnv.get("wchat.media.add.method") + "?access_token=" + wchatAccTokenService.getAccess_Token().getAccess_token();
        JSONObject jsonObject = null;
        try {

            //这块是用来处理如果上传的类型是video的类型的
            JSONObject j = new JSONObject();
            j.put("title", title);
            j.put("introduction", introduction);

            URL url = new URL(urlStr);
            String result = null;
            long filelength = file.length();
            String fileName = file.getName();
            String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            //  String type="video/mp4"; //我这里写死
            String typeStr = MediaEnums.getValueByCode(type) + "/" + suffix;
            /**
             *  你们需要在这里根据文件后缀suffix将type的值设置成对应的mime类型的值
             */
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false); // post方式不能使用缓存
            // 设置请求头信息
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");

            // 设置边界,这里的boundary是http协议里面的分割符，不懂的可以百度(http 协议 boundary)，这里boundary 可以是任意的值(111,2222)都行
            String BOUNDARY = "----------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            // 请求正文信息
            // 第一部分：

            StringBuilder sb = new StringBuilder();


            //这块是post提交type的值也就是文件对应的mime类型值
            sb.append("--"); // 必须多两道线 这里说明下，这两个横杠是http协议要求的，用来分隔提交的参数用的，不懂的可以看看http 协议头
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"type\" \r\n\r\n"); //这里是参数名，参数名和值之间要用两次
            sb.append(typeStr + "\r\n"); //参数的值

            //这块是上传video是必须的参数，你们可以在这里根据文件类型做if/else 判断
            sb.append("--"); // 必须多两道线
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"description\" \r\n\r\n");
            sb.append(j.toString() + "\r\n");

            /**
             * 这里重点说明下，上面两个参数完全可以写在url地址后面 就想我们平时url地址传参一样，
             * http://api.weixin.qq.com/cgi-bin/material/add_material?access_token=##ACCESS_TOKEN##&type=""&description={} 这样，如果写成这样，上面的
             * 那两个参数的代码就不用写了，不过media参数能否这样提交我没有试，感兴趣的可以试试
             */

            sb.append("--"); // 必须多两道线
            sb.append(BOUNDARY);
            sb.append("\r\n");
            //这里是media参数相关的信息，这里是否能分开下我没有试，感兴趣的可以试试
            sb.append("Content-Disposition: form-data;name=\"media\";filename=\""
                    + fileName + "\";filelength=\"" + filelength + "\" \r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            System.out.println(sb.toString());
            byte[] head = sb.toString().getBytes("utf-8");
            // 获得输出流
            OutputStream out = new DataOutputStream(con.getOutputStream());
            // 输出表头
            out.write(head);
            // 文件正文部分
            // 把文件已流文件的方式 推入到url中
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();
            // 结尾部分，这里结尾表示整体的参数的结尾，结尾要用"--"作为结束，这些都是http协议的规定
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
            out.write(foot);
            out.flush();
            out.close();
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = null;
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
            // 使用JSON-lib解析返回结果
            jsonObject = JSONObject.fromObject(result);
            if (jsonObject.has("media_id")) {
                System.out.println("media_id:" + jsonObject.getString("media_id"));
            } else {
                System.out.println(jsonObject.toString());
            }
            System.out.println("json:" + jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return jsonObject;
    }
}

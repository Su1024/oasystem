package com.oasystem.utils;

import com.alibaba.fastjson.JSON;
;
import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ClassName QiniuUtils
 * @Description
 * @Author suguoming
 * @Date 2020/2/19 5:45 下午
 */
@Component
public class QiniuUtils {
    @Value("${accessKey}")
    private String accessKey;
    @Value("${secretKey}")
    private String secretKey;
    @Value("${bucket}")
    private String bucket;


    public String upload(MultipartFile file) throws IOException {
        Configuration cfg = new Configuration(Region.region0());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, null, 3600, new StringMap()
                .put("returnBody", "{\"key\":\"$(key)\",\"name\":\"$(fname)\",\"type\":\"$(mimeType)\",\"size\":\"$(fsize)\"}"));
        String fileName = file.getOriginalFilename();
        FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
        Response response = uploadManager.put(fileInputStream, fileName, upToken, null, null);
        return response.bodyString();
    }

    public static void main(String[] args) throws Exception {

        Configuration cfg = new Configuration(Region.region0());
        UploadManager uploadManager = new UploadManager(cfg);

        String accessKey = "fid6x1_ScoclezQaxV3Xq0oK1hXxvDTUGgCyJfaa";
        String secretKey = "K1fzpWGjVBOgxkfIQ4zzV_Ji1PqK8-8HWckaa6HT";
        String bucket = "oasystem1";
        Auth auth = Auth.create(accessKey, secretKey);
//        String upToken = auth.uploadToken(bucket);
        String upToken = auth.uploadToken(bucket, null, 3600, new StringMap()
                .put("returnBody", "{\"key\":\"$(key)\",\"name\":\"$(fname)\",\"type\":\"$(mimeType)\",\"size\":\"$(fsize)\"}"));

        String key = "cc";
        String localFile = "/Users/sugm/Desktop/demo/a.pdf";
        StringMap stringMap = new StringMap();
        stringMap.put("returnBody", "$(name:fname)");

        Response response = uploadManager.put(localFile, key, upToken, null, null, false);
//        Response response = uploadManager.put(localFile, key, upToken,stringMap,null,false);
        System.out.println(response.toString());
//        //解析上传成功的结果
        System.out.println(response.bodyString().toString());
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        System.out.println(JSON.toJSONString(putRet));
    }


}

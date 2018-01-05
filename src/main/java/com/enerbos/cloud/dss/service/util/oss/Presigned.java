package com.enerbos.cloud.dss.service.util.oss;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.enerbos.cloud.dss.service.constants.ConfigConstants;
import com.enerbos.cloud.dss.service.constants.UploadSystemConstants;

import java.net.URL;
import java.util.Date;

/**
 * url 签名方法
 */
public class Presigned {

    public static String getSecurityUri(String uri ,int expireMin ){

        String endpoint = UploadSystemConstants.UPLOAD_CONFIG.get(ConfigConstants.ENDPOINT);//阿里云服务器地址
        String accessId = UploadSystemConstants.UPLOAD_CONFIG.get(ConfigConstants.ACCESS_ID);//key id
        String accessKey = UploadSystemConstants.UPLOAD_CONFIG.get(ConfigConstants.ACCESS_KEY);//秘钥
        String bucket = UploadSystemConstants.UPLOAD_CONFIG.get(ConfigConstants.BUCKET);//bucket

        OSSClient ossClient = new OSSClient(endpoint, accessId, accessKey);
        // 过期时间10分钟
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * expireMin );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucket, uri, HttpMethod.GET);
        req.setExpiration(expiration);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        return signedUrl.toString() ;
    }
}

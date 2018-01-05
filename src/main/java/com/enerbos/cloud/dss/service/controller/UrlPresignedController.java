package com.enerbos.cloud.dss.service.controller;


import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.enerbos.cloud.common.EnerbosMessage;
import com.enerbos.cloud.dss.service.constants.UploadSystemConstants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.security.Principal;
import java.util.Date;

/**
 * 为uri 添加访问权限
 */
@RestController
public class UrlPresignedController {

    @RequestMapping("/dss/uri/presigned")
    public EnerbosMessage urlPresigned(@RequestParam("uri") String uri , Principal user){


        String endpoint = UploadSystemConstants.UPLOAD_CONFIG.get("endpoint");//阿里云服务器地址
        String accessId = UploadSystemConstants.UPLOAD_CONFIG.get("accessId");//key id
        String accessKey = UploadSystemConstants.UPLOAD_CONFIG.get("accessKey");//秘钥
        String bucket = UploadSystemConstants.UPLOAD_CONFIG.get("bucket");//bucket

        OSSClient ossClient = new OSSClient(endpoint, accessId, accessKey);
        // 过期时间10分钟
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucket, uri, HttpMethod.GET);
        req.setExpiration(expiration);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        return EnerbosMessage.createSuccessMsg(signedUrl,"获取成功","");
    }
}

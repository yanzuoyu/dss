package com.enerbos.cloud.dss.service.controller;

import com.enerbos.cloud.dss.service.constants.UploadSystemConstants;
import com.enerbos.cloud.dss.service.domain.UploadLog;
import com.enerbos.cloud.dss.service.service.UploadLogService;
import com.enerbos.cloud.dss.service.util.ftp.FTPManagerFactory;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * All rights Reserved, Designed By 翼虎能源
 * Copyright:    Copyright(C) 2015-2017
 * Company   北京翼虎能源科技有限公司
 *
 * @author 严作宇
 * @version 1.0
 * @date 2017年5月31日 上午11:28:07
 * @Description 本地上传 处理
 */
@RestController
public class LocalUploadController {

    @Autowired
    private UploadLogService uploadLogService;

    @RequestMapping(value = "/dss/local/upload", method = RequestMethod.POST)
    public
    Map<String, Object> uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request, Principal user) throws Exception {
        String userName = user.getName();
        String path = UploadSystemConstants.UPLOAD_CONFIG.get("basedir")+user.getName()+ "/" + DateFormatUtils.format(new Date(), "yyyyMMdd")+"/";
        String fileName = file.getOriginalFilename();
        String suffix=fileName.substring(fileName.indexOf("."));
        String picId= String.valueOf(UUID.randomUUID());
        fileName=picId+suffix;

        String  type = request.getParameter("type");
        boolean uploadResult  = false ;
        //保存到本服务器上
        switch (type) {
            case "local" :
                //保存到本地
                File targetFile = new File(path);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                targetFile = new File(path ,fileName);
                file.transferTo(targetFile);
                uploadResult = true ;
                break;
            case "ftp":
                //保存在文件服务器上
                uploadResult = FTPManagerFactory.getFtpManager().uploadFile(path, fileName, file.getInputStream());
                break ;
        }
        UploadLog uploadLog = new UploadLog();
        uploadLog.setFilename(picId);

        uploadLog.setUserName(userName);
        uploadLog.setFilename(path+fileName);
        uploadLogService.insertUploadLog(uploadLog);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (uploadResult) {
            resultMap.put("original", file.getOriginalFilename());
            resultMap.put("size", file.getSize());
            resultMap.put("type", suffix);
            resultMap.put("url", path+"/"+fileName);
            resultMap.put("title", fileName);
            resultMap.put("state", "SUCCESS");
        }else{
            resultMap.put("state", "ERROR");
        }
        return resultMap;
    }
}

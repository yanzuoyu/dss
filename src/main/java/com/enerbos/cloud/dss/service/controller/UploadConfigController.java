package com.enerbos.cloud.dss.service.controller;


import com.enerbos.cloud.dss.service.constants.UploadSystemConstants;
import com.enerbos.cloud.dss.service.domain.UploadConfig;
import com.enerbos.cloud.dss.service.service.UploadConfigService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UploadConfigController {


    @Autowired
    private UploadConfigService  uploadConfigService ;

    /**
     * 查询 列表
     * @param pageNum
     * @param pageSize
     * @param user
     * @return
     */
    @RequestMapping("/dss/uploadConfigs/findPage")
    public PageInfo<UploadConfig> findPage(@RequestParam("pageNum") Integer  pageNum  , @RequestParam("pageSize") Integer pageSize ,    Principal  user ){

     return  uploadConfigService.findPage(pageNum ,pageSize) ;

    }

    @RequestMapping("/dss/upload/type")
     public Map<String,String> uploadType(){
        Map<String,String> result  = new HashMap<String,String>() ;
        result.put("type", UploadSystemConstants.UPLOAD_CONFIG.get("uploadType"));

        return result  ;
    }

}

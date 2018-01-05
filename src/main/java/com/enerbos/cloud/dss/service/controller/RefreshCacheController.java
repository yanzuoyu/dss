package com.enerbos.cloud.dss.service.controller;

import com.enerbos.cloud.common.EnerbosMessage;
import com.enerbos.cloud.dss.service.constants.ConfigConstants;
import com.enerbos.cloud.dss.service.constants.UploadSystemConstants;
import com.enerbos.cloud.dss.service.repository.dao.UploadConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By 翼虎能源
 * Copyright:    Copyright(C) 2015-2017
 * Company   北京翼虎能源科技有限公司
 *
 * @author 严作宇
 * @version 1.0
 * @date 2017年5月31日 上午11:28:07
 * @Description OSS上传处理
 */
@RestController
public class RefreshCacheController {

    @Autowired
    private UploadConfigDao uploadConfigDao;

    /**
     * 刷新缓存
     * @return
     */
    @RequestMapping("/dss/refresh")
    public EnerbosMessage refresh() {

        List<Map<String, String>> uploadConfigMap = uploadConfigDao.uploadConfig();

        for (Map<String, String> map : uploadConfigMap) {
            UploadSystemConstants.UPLOAD_CONFIG.put(map.get(ConfigConstants.NAME), map.get(ConfigConstants.CONTENT));
        }
        return EnerbosMessage.createSuccessMsg("","","");
    }

}
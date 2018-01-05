package com.enerbos.cloud.dss.service.service.impl;

import com.enerbos.cloud.dss.service.domain.UploadConfig;
import com.enerbos.cloud.dss.service.repository.dao.UploadConfigDao;
import com.enerbos.cloud.dss.service.repository.jpa.UploadLogRepository;
import com.enerbos.cloud.dss.service.service.UploadConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @Description 上传配置
 */
@Service
public class UploadConfigServiceImpl implements UploadConfigService {

    @Autowired
    private UploadConfigDao uploadConfigDao ;



    @Override
    public PageInfo<UploadConfig> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<UploadConfig> uploadConfigList =  uploadConfigDao.findUploadConfigs();
        return  new PageInfo<UploadConfig>(uploadConfigList);
    }

    @Override
    public List<Map<String, String>> uploadConfig() {
        return uploadConfigDao.uploadConfig();
    }



}

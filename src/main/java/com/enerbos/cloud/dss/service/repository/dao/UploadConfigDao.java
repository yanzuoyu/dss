package com.enerbos.cloud.dss.service.repository.dao;

import com.enerbos.cloud.dss.service.domain.UploadConfig;
import org.apache.ibatis.annotations.Mapper;

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
 * @Description 上传配置dao
 */
@Mapper
public interface UploadConfigDao {

    /**
     * 获取上传配置信息
     * @return
     */
    public List<Map<String,String>> uploadConfig();


    public List<UploadConfig> findUploadConfigs() ;


    Integer updateUploadConfig(UploadConfig uploadConfig);

}

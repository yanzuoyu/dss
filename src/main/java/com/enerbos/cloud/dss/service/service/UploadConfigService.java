package com.enerbos.cloud.dss.service.service;

import com.enerbos.cloud.dss.service.domain.UploadConfig;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by cosmos on 17/6/12.
 */
public interface UploadConfigService {
    PageInfo<UploadConfig> findPage(Integer pageNum, Integer pageSize);

    List<Map<String,String>> uploadConfig();

}

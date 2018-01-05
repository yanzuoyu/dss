package com.enerbos.cloud.dss.service.repository.dao;

import com.enerbos.cloud.dss.service.domain.UploadLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * All rights Reserved, Designed By 翼虎能源
 * Copyright:    Copyright(C) 2015-2017
 * Company   北京翼虎能源科技有限公司
 *
 * @author 严作宇
 * @version 1.0
 * @date 2017年5月31日 上午11:28:07
 * @Description 上传日志 dao
 */
@Mapper
public interface UploadLogDao {
    int deleteById(@Param("id") Integer id);

    int insert(UploadLog record);

    UploadLog findById(@Param("id") Integer id);

}
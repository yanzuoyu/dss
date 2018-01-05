package com.enerbos.cloud.dss.service.service;

import com.enerbos.cloud.dss.service.domain.Fileinfo;

import java.util.List;

/**
 * All rights Reserved, Designed By 翼虎能源
 * Copyright:    Copyright(C) 2015-2017
 * Company   北京翼虎能源科技有限公司
 *
 * @author 严作宇
 * @version 1.0
 * @date 17/6/13 下午3:27
 * @Description  上传 信息 service
 */
public interface FileInfoService {

    List<Fileinfo> save(List<Fileinfo> fileinfos, String loginName);

    List<String> deleteByIds(String[] ids, String userName);

    List<Fileinfo>  findByQuoteTypeAndQuoteId(String quoteType, String quoteId);

    void updateQuoteId(String[] ids, String quoteId);
}

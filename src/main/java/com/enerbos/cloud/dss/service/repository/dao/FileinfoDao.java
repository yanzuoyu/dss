package com.enerbos.cloud.dss.service.repository.dao;

import com.enerbos.cloud.dss.service.domain.Fileinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * All rights Reserved, Designed By 翼虎能源
 * Copyright:    Copyright(C) 2015-2017
 * Company   北京翼虎能源科技有限公司
 *
 * @author 严作宇
 * @version 1.0
 * @date 17/6/13 下午3:47
 * @Description
 */
@Mapper
public interface FileinfoDao {

    public void insert(Fileinfo fileinfo);

    Fileinfo findById(@Param("id") String id);

    void deleteById(@Param("id") String id);

   List<Fileinfo> findByQuoteTypeAndQuoteId(@Param("quoteType")   String quoteType  , @Param("quoteId") String quoteId);

    void deleteByQuoteIdAndQuoteType(@Param("quoteId") String quoteId,@Param("quoteType") String quoteType);

    void updateQuoteId(@Param("id")String id,@Param("quoteId") String quoteId);
}

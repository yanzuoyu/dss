package com.enerbos.cloud.dss.service.service.impl;

import com.enerbos.cloud.dss.service.repository.jpa.UploadLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enerbos.cloud.dss.service.domain.UploadLog;
import com.enerbos.cloud.dss.service.repository.dao.UploadLogDao;
import com.enerbos.cloud.dss.service.service.UploadLogService;
/**
 * All rights Reserved, Designed By 翼虎能源
 * Copyright:    Copyright(C) 2015-2017
 * Company   北京翼虎能源科技有限公司
 *
 * @author 严作宇
 * @version 1.0
 * @date 2017年5月31日 上午11:28:07
 * @Description 上传日志实现
 */
@Service
public class UploadLogServiceImpl implements UploadLogService {

	@Autowired
	private UploadLogDao uploadLogDao ;

	@Autowired
	private UploadLogRepository uploadLogRepository ;

	@Override
	public void insertUploadLog(UploadLog uploadLog) {
		uploadLogRepository.save(uploadLog);
	}
}

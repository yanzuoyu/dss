package com.enerbos.cloud.dss.service.util.ftp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * All rights Reserved, Designed By 翼虎能源
 * Copyright:    Copyright(C) 2015-2017
 * Company   北京翼虎能源科技有限公司
 *
 * @author 严作宇
 * @version 1.0
 * @date 2017年5月31日 上午11:28:07
 * @Description ftp 工厂
 */
public class FTPManagerFactory {
    
    private static final Logger logger = LoggerFactory.getLogger(SFTPUtils.class);
    
    private static FTPManager ftpManager;

    public static FTPManager getFtpManager() {
        return ftpManager;
    }
    
    static {
        initFTPManager();
    }
    
    /**
     * 初始化缓存管理器
     */
    private static void initFTPManager() {
          String  manager = "com.fbd.core.common.ftp.SFTPUtils";
        try {
            ftpManager = (FTPManager) Class.forName(manager).newInstance();
        } catch (Exception e) {
            logger.warn("FTP[class=" + manager + "]  init error:", e);
        }
    }
    
}

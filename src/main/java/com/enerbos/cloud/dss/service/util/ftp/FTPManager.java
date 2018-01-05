package com.enerbos.cloud.dss.service.util.ftp;

import java.io.InputStream;
/**
 * All rights Reserved, Designed By 翼虎能源
 * Copyright:    Copyright(C) 2015-2017
 * Company   北京翼虎能源科技有限公司
 *
 * @author 严作宇
 * @version 1.0
 * @date 2017年5月31日 上午11:28:07
 * @Description  ftp方法 接口
 */
public interface FTPManager {
    
    /**
     * Description: 向FTP服务器上传文件
     * @param path FTP服务器保存目录
     * @param filename 上传到FTP服务器上的文件名
     * @param input 输入流
     * @return 成功返回true，否则返回false
     */
    boolean uploadFile(String path, String filename, InputStream input);
    
    /**
     * Description: 向FTP服务器下载文件
     * @param path FTP服务器下载目录
     * @param fileName 上传到FTP服务器上的文件名
     * @return 输出流
     */
    InputStream  downFile(String path, String fileName); 
    
    boolean deleteFile(String filename);
}

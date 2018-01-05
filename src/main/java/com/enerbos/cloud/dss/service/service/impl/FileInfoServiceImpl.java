package com.enerbos.cloud.dss.service.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.enerbos.cloud.common.EnerbosException;
import com.enerbos.cloud.dss.service.constants.ConfigConstants;
import com.enerbos.cloud.dss.service.constants.UploadSystemConstants;
import com.enerbos.cloud.dss.service.domain.Fileinfo;
import com.enerbos.cloud.dss.service.domain.UploadLog;
import com.enerbos.cloud.dss.service.repository.dao.FileinfoDao;
import com.enerbos.cloud.dss.service.repository.jpa.FileinfoRepository;
import com.enerbos.cloud.dss.service.repository.jpa.UploadLogRepository;
import com.enerbos.cloud.dss.service.service.FileInfoService;
import com.enerbos.cloud.dss.service.util.oss.Presigned;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights Reserved, Designed By 翼虎能源
 * Copyright:    Copyright(C) 2015-2017
 * Company   北京翼虎能源科技有限公司
 *
 * @author 严作宇
 * @version 1.0
 * @date 17/6/13 下午3:28
 * @Description
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileinfoDao fileInfoDao ;

    @Autowired
    private FileinfoRepository fileinfoRepository ;


    @Autowired
    private UploadLogRepository uploadLogRepository ;

    @Override
    @Transactional
    public List<Fileinfo> save(List<Fileinfo> fileinfos, String loginName) {

        for (Fileinfo fileinfo : fileinfos) {
            fileinfo.setCreateUser(loginName);
            fileinfo = fileinfoRepository.save(fileinfo) ;
        }
        return fileinfos;
    }

    @Transactional
    @Override
    public List<String> deleteByIds(String[] ids, String userName) {
        String endpoint = UploadSystemConstants.UPLOAD_CONFIG.get(ConfigConstants.ENDPOINT) ;
        String accessKeyId = UploadSystemConstants.UPLOAD_CONFIG.get(ConfigConstants.ACCESS_ID) ;
        String accessKeySecret =UploadSystemConstants.UPLOAD_CONFIG.get(ConfigConstants.ACCESS_KEY) ;
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        List<String> deletekeys = new ArrayList<String>();
        for (String id: ids
             ) {
         Fileinfo fileinfo =   fileInfoDao.findById(id) ;
         if(fileinfo == null){
             throw  new EnerbosException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"记录不存在");
         }
            UploadLog log = new UploadLog() ;
            log.setUserName(userName);
            log.setAction(ConfigConstants.ACTION_DELETE);
            uploadLogRepository.save(log) ;
            deletekeys.add(fileinfo.getPath()) ;
            fileinfoRepository.delete(id);
        }

        // 删除Objects
        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(UploadSystemConstants.UPLOAD_CONFIG.get(ConfigConstants.BUCKET));

        //设置删除的keys
        deleteObjectsRequest.setKeys(deletekeys);
        //详细模式返回详细信息
        deleteObjectsRequest.setQuiet(false);
        DeleteObjectsResult deletedObjects = ossClient.deleteObjects(deleteObjectsRequest);
        List<String> result= deletedObjects.getDeletedObjects();

        // 关闭 服务
        ossClient.shutdown();
        return result ;
    }

    @Override
    public List<Fileinfo> findByQuoteTypeAndQuoteId(String quoteType, String quoteId) {
          List<Fileinfo> fileinfos = fileInfoDao.findByQuoteTypeAndQuoteId(quoteType,quoteId);
        for (Fileinfo fileinfo: fileinfos
             ) {
            if(StringUtils.isNotEmpty(fileinfo.getPath())){
                String imagePath  = UploadSystemConstants.UPLOAD_CONFIG.get(ConfigConstants.HTTP_PATH) +fileinfo.getPath()  ;
                String relativePath  = Presigned.getSecurityUri(imagePath, 30).split(UploadSystemConstants.UPLOAD_CONFIG.get(ConfigConstants.HTTP_PATH))[1];
                fileinfo.setPath(relativePath);
            }
        }
          return fileinfos ;
    }

    @Override
    @Transactional
    public void updateQuoteId(String[] ids, String quoteId) {
        for (String id: ids
                ) {
            Fileinfo fileinfo =   fileInfoDao.findById(id) ;
            if(fileinfo == null){
                throw  new EnerbosException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"记录不存在");
            }
            fileInfoDao.updateQuoteId(id,quoteId);
        }
    }

}

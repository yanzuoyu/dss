package com.enerbos.cloud.dss.service.controller;

import com.enerbos.cloud.common.EnerbosMessage;
import com.enerbos.cloud.dss.service.constants.ConfigConstants;
import com.enerbos.cloud.dss.service.constants.UploadSystemConstants;
import com.enerbos.cloud.dss.service.domain.Fileinfo;
import com.enerbos.cloud.dss.service.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By 翼虎能源
 * Copyright:    Copyright(C) 2015-2017
 * Company   北京翼虎能源科技有限公司
 *
 * @author 严作宇
 * @version 1.0
 * @date 17/6/13 下午1:27
 * @Description 文件上传 保存 controller
 */

@RestController
public class FileManageController {


    @Autowired
    private FileInfoService fileInfoService ;

    /** 批量保存文件
     * @param fileinfos
     * @param user
     * @return
     */
    @RequestMapping("/dss/fileinfos/save")
    public EnerbosMessage save(@RequestBody List<Fileinfo> fileinfos, Principal user ){
        try {

            fileinfos = fileInfoService.save(fileinfos,user.getName()) ;
           return  EnerbosMessage.createSuccessMsg(fileinfos,"保存成功","");
        }catch (Exception e){
           return  EnerbosMessage.createErrorMsg(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"保存失败",e.getMessage());
        }
    }

    /**
     * 批量修改 文件信息
     * @param ids  文件 id
     * @param quoteId
     * @param user
     * @return
     */
    @RequestMapping("/dss/fileinfos/update")
    public EnerbosMessage update(@RequestParam("ids") String[] ids,@RequestParam("quoteId")String quoteId , Principal user ){
        try {

            fileInfoService.updateQuoteId(ids,quoteId);
            return  EnerbosMessage.createSuccessMsg("","保存成功","");
        }catch (Exception e){
            return  EnerbosMessage.createErrorMsg(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"保存失败",e.getMessage());
        }
    }

    /**
     * 批量删除
     * @param ids
     * @param user
     * @return
     */
    @RequestMapping("/dss/fileinfos/delete")
    public EnerbosMessage delete( @RequestParam("ids") String[]  ids ,Principal user ){
        try{

            return  EnerbosMessage.createSuccessMsg(fileInfoService.deleteByIds(ids,user.getName()),"删除成功","");
        } catch ( Exception e ) {
            return EnerbosMessage.createErrorMsg(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"删除失败",e.getMessage());
        }
    }

    /**
     * 根据业务类型 和业务id 查询文件
     * @param quoteType
     * @param quoteId
     * @param user
     * @return
     */
    @RequestMapping("/dss/fileinfos/findByQuoteTypeAndQuoteId")
    public EnerbosMessage findByQuoteTypeAndQuoteId( @RequestParam("quoteType")  String  quoteType,@RequestParam("quoteId") String quoteId,   Principal user ){
        try{
            Map<String ,Object> result = new HashMap<String,Object>();
            result.put(ConfigConstants.IMAGES,fileInfoService.findByQuoteTypeAndQuoteId(quoteType,quoteId));
            result.put(ConfigConstants.HTTP_PATH, UploadSystemConstants.UPLOAD_CONFIG.get(ConfigConstants.HTTP_PATH));
            return  EnerbosMessage.createSuccessMsg(result,"查询成功","");
        } catch ( Exception e ) {
            return EnerbosMessage.createErrorMsg(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"查询失败",e.getMessage());
        }
    }

}

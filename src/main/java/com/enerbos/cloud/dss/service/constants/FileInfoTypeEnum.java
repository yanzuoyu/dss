package com.enerbos.cloud.dss.service.constants;

/**
 * All rights Reserved, Designed By 翼虎能源
 * Copyright:    Copyright(C) 2015-2017
 * Company   北京翼虎能源科技有限公司
 *
 * @author 严作宇
 * @version 1.0
 * @date 17/6/13 下午1:56
 * @Description  枚举一下 类型判断 使用
 */
public  enum FileInfoTypeEnum {
    assetImg,//("设备图片")
    thumbImg,//("设备图片缩略图")
    assetData,//("设备图纸资料")
    itemImg,//("物资台账图片资料")
    workOrderImg_report ,//维保工单执行汇报图片
    workOrderImg, //("维保工单验收图片")
    dispatchOrderImg_report,//派工单执行汇报图片
    defectdocument,//缺陷单待提报图片
    defectOrderImg_submit,//整改单提报图片
    defectOrderImg_report,//整改单待汇报图片
    defectOrderImg_confirm,//整改单待验收图片
    repairOrderImg_submit,//("报修工单提报图片")
    repairOrderImg, //("报修工单执行汇报图片")
    repairOrderImg_accept,//("报修工单验收图片")
    dispatchOrderImg, // ("派工单执行汇报图片")
    userInfoImgTop, //("用户头像_首页显示")
    siteImg, //("站点图片")
    portalPointImg_report,//巡检汇报时的图片
    dailyTaskImg_submit,//
    dispatchOrderImg_submit, //派工单提报
    dispatchOrderImg_accept,//派工单验收
    userInfoImg ; //("用户头像_个人信息显示")

    public static boolean contains(String type){
        for(FileInfoTypeEnum typeEnum : FileInfoTypeEnum.values()){
            if(typeEnum.name().equals(type)){
                return true;
            }
        }
        return false;
    }
}

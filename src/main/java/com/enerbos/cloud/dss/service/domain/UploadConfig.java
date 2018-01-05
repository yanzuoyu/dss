package com.enerbos.cloud.dss.service.domain;

import com.enerbos.cloud.jpa.EnerbosBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * All rights Reserved, Designed By 翼虎能源
 * Copyright:    Copyright(C) 2015-2017
 * Company   北京翼虎能源科技有限公司
 *
 * @author 严作宇
 * @version 1.0
 * @date 2017年6月11日 上午11:28:07
 * @Description 上传日志 配置
 */
@Entity
@Table(name="dss_upload_config")
public class UploadConfig extends EnerbosBaseEntity{

    @Column
    private String name  ;
    @Column
    private String type ;
    @Column
    private String content  ;
    @Column
    private String description ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UploadConfig{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

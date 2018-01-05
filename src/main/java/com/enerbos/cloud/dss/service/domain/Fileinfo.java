package com.enerbos.cloud.dss.service.domain;

import com.enerbos.cloud.jpa.EnerbosBaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 图片信息实体
 */
@Entity
@Table(name = "dss_fileinfo")
public class Fileinfo extends EnerbosBaseEntity{



    /**
     * 引用id
     */
    @Column
    private String quoteId;

    /**
     * 引用类型
     */
    @Column
    private String quoteType;

    /**
     * 文件名称
     */
    @Column
    private String fileName;

    /**
     * 文件描述
     */
    @Column
    private String fileDescription;

    /**
     * 文件大小(字节数)
     */
    @Column
    private Long fileSize;


    /**
     * 保存路径
     */
    @Column
    private String path;

    /**
     * 所属组织
     */
    @Column
    private String orgId;

    /**
     * 所属站点
     */
    @Column
    private String siteId;


    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    @Override
    public String toString() {
        return "Fileinfo{" +
                "quoteId='" + quoteId + '\'' +
                ", quoteType='" + quoteType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileDescription='" + fileDescription + '\'' +
                ", fileSize=" + fileSize +
                ", path='" + path + '\'' +
                ", orgId='" + orgId + '\'' +
                ", siteId='" + siteId + '\'' +
                '}';
    }
}

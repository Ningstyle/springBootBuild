package com.small.business.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

@Data
public class VFemsCsrBoltLog extends Model {
    private Long id;

    private Long boltId;

    private String fileName;

    private String filePath;

    private String equipmentInfo;

    private String operator;

    private String upName;

    private String nodeNo;

    private String boltNo;

    private String dimension;

    private String targetValue;

    private Integer isStandard;

    private Date uploadTime;

    private Date updateTime;

    private String updateUserId;

    private String updateUserName;

    private String createUserId;

    private String createUserName;

    private String upId;

    private String upSeatno;

    private String upRunningPositionNo;

    private String upPjId;

    private String upPjName;

    private String constructionStage;

    private String constructionStageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBoltId() {
        return boltId;
    }

    public void setBoltId(Long boltId) {
        this.boltId = boltId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getEquipmentInfo() {
        return equipmentInfo;
    }

    public void setEquipmentInfo(String equipmentInfo) {
        this.equipmentInfo = equipmentInfo == null ? null : equipmentInfo.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getUpName() {
        return upName;
    }

    public void setUpName(String upName) {
        this.upName = upName == null ? null : upName.trim();
    }

    public String getNodeNo() {
        return nodeNo;
    }

    public void setNodeNo(String nodeNo) {
        this.nodeNo = nodeNo == null ? null : nodeNo.trim();
    }

    public String getBoltNo() {
        return boltNo;
    }

    public void setBoltNo(String boltNo) {
        this.boltNo = boltNo == null ? null : boltNo.trim();
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension == null ? null : dimension.trim();
    }

    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue == null ? null : targetValue.trim();
    }

    public Integer getIsStandard() {
        return isStandard;
    }

    public void setIsStandard(Integer isStandard) {
        this.isStandard = isStandard;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName == null ? null : updateUserName.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public String getUpId() {
        return upId;
    }

    public void setUpId(String upId) {
        this.upId = upId == null ? null : upId.trim();
    }

    public String getUpSeatno() {
        return upSeatno;
    }

    public void setUpSeatno(String upSeatno) {
        this.upSeatno = upSeatno == null ? null : upSeatno.trim();
    }

    public String getUpRunningPositionNo() {
        return upRunningPositionNo;
    }

    public void setUpRunningPositionNo(String upRunningPositionNo) {
        this.upRunningPositionNo = upRunningPositionNo == null ? null : upRunningPositionNo.trim();
    }

    public String getUpPjId() {
        return upPjId;
    }

    public void setUpPjId(String upPjId) {
        this.upPjId = upPjId == null ? null : upPjId.trim();
    }

    public String getUpPjName() {
        return upPjName;
    }

    public void setUpPjName(String upPjName) {
        this.upPjName = upPjName == null ? null : upPjName.trim();
    }

    public String getConstructionStage() {
        return constructionStage;
    }

    public void setConstructionStage(String constructionStage) {
        this.constructionStage = constructionStage == null ? null : constructionStage.trim();
    }

    public String getConstructionStageId() {
        return constructionStageId;
    }

    public void setConstructionStageId(String constructionStageId) {
        this.constructionStageId = constructionStageId == null ? null : constructionStageId.trim();
    }
}
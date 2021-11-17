package com.small.business.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class VFemsCsrConstructionOrder extends Model<VFemsCsrConstructionOrder> {
    private String pjId;

    private String pjNo;

    private String pjName;

    private String bgName;

    private String bgId;

    private String asswoId;

    private String asswoNo;

    private String asswmstTypeType;

    private String asswmstType;

    private String upId;

    private String upRunningPositionNo;

    private String upSeatno;

    private String personLiableName;

    private String personLiableId;

    private String asswoStatus;

    private String asswoStatusName;

    private String asswoStartTime;

    private String asswoEndTime;

    public String getPjId() {
        return pjId;
    }

    public void setPjId(String pjId) {
        this.pjId = pjId == null ? null : pjId.trim();
    }

    public String getPjNo() {
        return pjNo;
    }

    public void setPjNo(String pjNo) {
        this.pjNo = pjNo == null ? null : pjNo.trim();
    }

    public String getPjName() {
        return pjName;
    }

    public void setPjName(String pjName) {
        this.pjName = pjName == null ? null : pjName.trim();
    }

    public String getBgName() {
        return bgName;
    }

    public void setBgName(String bgName) {
        this.bgName = bgName == null ? null : bgName.trim();
    }

    public String getBgId() {
        return bgId;
    }

    public void setBgId(String bgId) {
        this.bgId = bgId == null ? null : bgId.trim();
    }

    public String getAsswoId() {
        return asswoId;
    }

    public void setAsswoId(String asswoId) {
        this.asswoId = asswoId == null ? null : asswoId.trim();
    }

    public String getAsswoNo() {
        return asswoNo;
    }

    public void setAsswoNo(String asswoNo) {
        this.asswoNo = asswoNo == null ? null : asswoNo.trim();
    }

    public String getAsswmstTypeType() {
        return asswmstTypeType;
    }

    public void setAsswmstTypeType(String asswmstTypeType) {
        this.asswmstTypeType = asswmstTypeType == null ? null : asswmstTypeType.trim();
    }

    public String getAsswmstType() {
        return asswmstType;
    }

    public void setAsswmstType(String asswmstType) {
        this.asswmstType = asswmstType == null ? null : asswmstType.trim();
    }

    public String getUpId() {
        return upId;
    }

    public void setUpId(String upId) {
        this.upId = upId == null ? null : upId.trim();
    }

    public String getUpRunningPositionNo() {
        return upRunningPositionNo;
    }

    public void setUpRunningPositionNo(String upRunningPositionNo) {
        this.upRunningPositionNo = upRunningPositionNo == null ? null : upRunningPositionNo.trim();
    }

    public String getUpSeatno() {
        return upSeatno;
    }

    public void setUpSeatno(String upSeatno) {
        this.upSeatno = upSeatno == null ? null : upSeatno.trim();
    }

    public String getPersonLiableName() {
        return personLiableName;
    }

    public void setPersonLiableName(String personLiableName) {
        this.personLiableName = personLiableName == null ? null : personLiableName.trim();
    }

    public String getPersonLiableId() {
        return personLiableId;
    }

    public void setPersonLiableId(String personLiableId) {
        this.personLiableId = personLiableId == null ? null : personLiableId.trim();
    }

    public String getAsswoStatus() {
        return asswoStatus;
    }

    public void setAsswoStatus(String asswoStatus) {
        this.asswoStatus = asswoStatus == null ? null : asswoStatus.trim();
    }

    public String getAsswoStatusName() {
        return asswoStatusName;
    }

    public void setAsswoStatusName(String asswoStatusName) {
        this.asswoStatusName = asswoStatusName == null ? null : asswoStatusName.trim();
    }

    public String getAsswoStartTime() {
        return asswoStartTime;
    }

    public void setAsswoStartTime(String asswoStartTime) {
        this.asswoStartTime = asswoStartTime == null ? null : asswoStartTime.trim();
    }

    public String getAsswoEndTime() {
        return asswoEndTime;
    }

    public void setAsswoEndTime(String asswoEndTime) {
        this.asswoEndTime = asswoEndTime == null ? null : asswoEndTime.trim();
    }
}
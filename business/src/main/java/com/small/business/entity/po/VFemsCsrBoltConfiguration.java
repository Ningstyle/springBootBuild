package com.small.business.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

@Data
public class VFemsCsrBoltConfiguration extends Model<VFemsCsrBoltConfiguration> {
    private Long id;

    private String connId;

    private String upId;

    private String node;

    private Long nodeId;

    private String nodeName;

    private Long conId;

    private String blade;

    private Long num;

    private String specification;

    private String constructionMoment;

    private String hertzMoment;

    private String yearMoment;

    private String constructionPreload;

    private String hertzPreload;

    private String yearPreload;

    private Date createTime;

    private String createUserId;

    private String createUserName;

    private Date updateTime;

    private String updateUserId;

    private String updateUserName;

    private String role;

    private String hertzMomentTimes;

    private String yearMomentTimes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConnId() {
        return connId;
    }

    public void setConnId(String connId) {
        this.connId = connId == null ? null : connId.trim();
    }

    public String getUpId() {
        return upId;
    }

    public void setUpId(String upId) {
        this.upId = upId == null ? null : upId.trim();
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node == null ? null : node.trim();
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public Long getConId() {
        return conId;
    }

    public void setConId(Long conId) {
        this.conId = conId;
    }

    public String getBlade() {
        return blade;
    }

    public void setBlade(String blade) {
        this.blade = blade == null ? null : blade.trim();
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public String getConstructionMoment() {
        return constructionMoment;
    }

    public void setConstructionMoment(String constructionMoment) {
        this.constructionMoment = constructionMoment == null ? null : constructionMoment.trim();
    }

    public String getHertzMoment() {
        return hertzMoment;
    }

    public void setHertzMoment(String hertzMoment) {
        this.hertzMoment = hertzMoment == null ? null : hertzMoment.trim();
    }

    public String getYearMoment() {
        return yearMoment;
    }

    public void setYearMoment(String yearMoment) {
        this.yearMoment = yearMoment == null ? null : yearMoment.trim();
    }

    public String getConstructionPreload() {
        return constructionPreload;
    }

    public void setConstructionPreload(String constructionPreload) {
        this.constructionPreload = constructionPreload == null ? null : constructionPreload.trim();
    }

    public String getHertzPreload() {
        return hertzPreload;
    }

    public void setHertzPreload(String hertzPreload) {
        this.hertzPreload = hertzPreload == null ? null : hertzPreload.trim();
    }

    public String getYearPreload() {
        return yearPreload;
    }

    public void setYearPreload(String yearPreload) {
        this.yearPreload = yearPreload == null ? null : yearPreload.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getHertzMomentTimes() {
        return hertzMomentTimes;
    }

    public void setHertzMomentTimes(String hertzMomentTimes) {
        this.hertzMomentTimes = hertzMomentTimes == null ? null : hertzMomentTimes.trim();
    }

    public String getYearMomentTimes() {
        return yearMomentTimes;
    }

    public void setYearMomentTimes(String yearMomentTimes) {
        this.yearMomentTimes = yearMomentTimes == null ? null : yearMomentTimes.trim();
    }
}
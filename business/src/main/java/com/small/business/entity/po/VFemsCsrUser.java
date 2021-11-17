package com.small.business.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class VFemsCsrUser extends Model<VFemsCsrUser> {
    private String userId;

    private String userName;

    private String userType;

    private String pgName;

    private String basBgId;

    private String bgId;

    private String bgName;

    private String roleName;

    private String systemcode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getPgName() {
        return pgName;
    }

    public void setPgName(String pgName) {
        this.pgName = pgName == null ? null : pgName.trim();
    }

    public String getBasBgId() {
        return basBgId;
    }

    public void setBasBgId(String basBgId) {
        this.basBgId = basBgId == null ? null : basBgId.trim();
    }

    public String getBgId() {
        return bgId;
    }

    public void setBgId(String bgId) {
        this.bgId = bgId == null ? null : bgId.trim();
    }

    public String getBgName() {
        return bgName;
    }

    public void setBgName(String bgName) {
        this.bgName = bgName == null ? null : bgName.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getSystemcode() {
        return systemcode;
    }

    public void setSystemcode(String systemcode) {
        this.systemcode = systemcode == null ? null : systemcode.trim();
    }
}
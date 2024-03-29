package com.zb.misscmszb.bean;

import com.zb.misscmszb.core.enumeration.UserLevel;

public class MetaInfo {

    private String permission;
    private String module;
    private String identity;
    private UserLevel userLevel;

    public MetaInfo(String permission, String module, String identity, UserLevel userLevel) {
        this.permission = permission;
        this.module = module;
        this.identity = identity;
        this.userLevel = userLevel;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public String toString() {
        return "MetaInfo{" +
                "permission='" + permission + '\'' +
                ", module='" + module + '\'' +
                ", identity='" + identity + '\'' +
                ", userLevel=" + userLevel +
                '}';
    }
}

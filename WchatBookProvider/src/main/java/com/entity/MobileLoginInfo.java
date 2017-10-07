package com.entity;

/**
 * Created by Administrator on 2017/10/6.
 */
public class MobileLoginInfo extends AbstractEntity {
    private String appToken;//手机token
    private String serial;//硬件序列号（设备id）
    private String model; //手机型号（例如小米/华为）
    private String appUserName;//设备名称
    private  String versionCode;//版本号

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAppUserName() {
        return appUserName;
    }

    public void setAppUserName(String appUserName) {
        this.appUserName = appUserName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }
}

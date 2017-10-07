package com.entity;

/**
 * Created by Administrator on 2017/10/6.
 */
public class MobileLoginInfo extends AbstractEntity {
    private String appToken;//手机token
    private String serial;//硬件序列号（设备id）
    private String model; //手机型号（例如小米/华为）
    private String mobilename;//设备名称
    private  String version;//版本号

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

    public String getMobilename() {
        return mobilename;
    }

    public void setMobilename(String mobilename) {
        this.mobilename = mobilename;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

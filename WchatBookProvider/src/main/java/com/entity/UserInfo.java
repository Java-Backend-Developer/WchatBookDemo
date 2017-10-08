package com.entity;


/**
 * created by lx
 */
public  class UserInfo extends AbstractEntity {

    private Integer type;

    private Picture avatar;

    private String realName;

    private Byte sex;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Picture getAvatar() {
        return avatar;
    }

    public void setAvatar(Picture avatar) {
        this.avatar = avatar;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }
}

package com.test;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/29.
 */
public class Test implements Serializable{
    public String environment;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}

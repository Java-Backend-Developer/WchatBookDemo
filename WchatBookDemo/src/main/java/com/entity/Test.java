package com.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/5.
 */
public class Test extends AbstractEntity implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

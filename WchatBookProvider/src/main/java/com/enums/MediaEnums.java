package com.enums;

import com.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/1.
 */
public enum MediaEnums {

    MEDIA_IMAGE(1,"image"),
    MEDIA_VOICE(2,"voice"),
    MEDIA_VIDEO(3,"video"),
    MEDIA_THUMB(4,"thumb");



    private int code;
    private String value;

    MediaEnums(int code, String value){
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static String getValueByCode(int code) {
        for (MediaEnums m : MediaEnums.values()) {
            if (m.getCode() == code) {
                return m.value;
            }
        }
        return null;
    }

    public static Integer getCodeByValue(String value) {
        if(StringUtil.isEmpty(value)){
            return null;
        }
        for (MediaEnums m : MediaEnums.values()) {
            if (m.getValue().equals(value.trim())) {
                return m.code;
            }
        }
        return null;
    }

    public static Map<String, Integer> getMapValues() {
        Map<String, Integer> enumsMap = new HashMap<String, Integer>();
        for (MediaEnums m : MediaEnums.values()) {
            enumsMap.put(m.name(), m.getCode());
        }
        return enumsMap;
    }

    public static MediaEnums getMediaEnumsByCode(int code) {
        for (MediaEnums m : MediaEnums.values()) {
            if (m.getCode() == code) {
                return m;
            }
        }
        return null;
    }
}

/**
 * Copyright 2014-2015 www.goujiawang.com
 * All rights reserved.
 * 
 * @project
 * @author Flouny.Caesar
 * @version 2.0
 * @date 2014-11-26
 */
package com.util;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtil 工具类
 * 
 * @author Flouny.Caesar
 * 
 */
public class StringUtil extends StringUtils {

    public static final String CHARSET_ENCODING = "UTF-8";
    
    /**
     * 根据正则表达式替换字符，返回新的字符
     * @param source
     * @param regexStr
     * @return
     */
    public static String replaceByRegex(String source , String regexStr){
    	Pattern pattern = Pattern.compile(regexStr);
    	Matcher matcher = pattern.matcher(source);
    	if(matcher.find()){
    		return matcher.replaceAll("");
    	}
    	return null ;
    }
    /**
     * 根据正则表达式获取第一个匹配字符
     * @param source
     * @return
     */
    public static String getNumberByRegexFirst(String source){
    	Pattern pattern = Pattern.compile("\\d+");
    	Matcher matcher = pattern.matcher(source);
    	if(matcher.find()){
    		return matcher.group();
    	}
    	return null ;
    }
    /**
     * 根据正则表达式获取所有匹配字符
     * @param source
     * @return
     */
    public static String getNumberByRegexAll(String source){
    	Pattern pattern = Pattern.compile("\\d+");
    	Matcher matcher = pattern.matcher(source);
    	StringBuilder numberBuilder = new StringBuilder() ;
    	while(matcher.find()){
    		numberBuilder.append(matcher.group());
    	}
    	return numberBuilder.toString() ;
    }
    
    /**
     * 如果字符串为空返回默认值
     * @param source
     * @param defaultString
     * @return
     */
    public static String getStringDefault(String source,String defaultString){
    	if(source==null || source.trim().equals("")){
    		return defaultString ;
    	}else{
    		return source;
    	}
    }

    /**
     * 
     * @Description 字符截取
     * @param str
     * @return
     * @author chenghao
     * @date Sep 17, 2015 11:01:25 AM
     */
    public static String intercept(String str, int length, String appendStr) {
        if (str.length() <= length)
            return str;
        else
            return str.substring(0, length) + appendStr;
    }

    /**
     * 通过字符串驻留池来进行比较
     * 
     * @param str1
     * @param str2
     * @return
     */
    public static boolean intern(String str1, String str2) {
        if (str1 == null)
            return str2 == null;

        str1 = str1.intern();

        return (str1 == str2) ? true : false;
    }

    /**
     * 将String aaBc 转为 aa_bc的格式 用于java bean 属性转为数据库字段名
     * 
     * @param str
     * @return
     */
    public static String propertyToFieldName(String str) {
        if (isEmpty(str))
            return str;

        if (str.charAt(0) > 'A' && str.charAt(0) < 'Z')
            return str;

        if (Character.isUpperCase(str.charAt(0)))
            str = Character.toString(Character.toLowerCase(str.charAt(0))) + str.substring(1);

        for (int i = 1; i < str.length(); i++) {
            char a = str.charAt(i);
            if (a >= 'A' && a <= 'Z') {
                if (str.charAt(i - 1) != '_') {
                    str = str.replace(String.valueOf(a), ("_".concat(String.valueOf(a))).toLowerCase());
                    i++;
                }

                continue;
            }
        }

        return str;
    }

    /**
     * 返回截取字符串
     * 
     * @param str
     * @param num
     * @return
     */
    public static String omit(String str, int num) {

        if (StringUtil.isBlank(str))
            return null;
        if (str.length() < num)
            return str;

        return num > 0 ? str.substring(0, num) : str;
    }

    /**
     * 截断字符串，只适用中文和单字节表示字符的国家
     * 
     * @param str
     * @param byteLength
     * @return
     */
    public static String limit(String str, int byteLength) {
        if (isBlank(str))
            return null;
        if (byteLength <= 0)
            return null;

        try {
            if (str.getBytes(StringUtil.CHARSET_ENCODING).length <= byteLength)
                return str;
        } catch (UnsupportedEncodingException e) {

            return null;
        }

        StringBuffer buff = new StringBuffer();
        int index = 0;
        char c;
        char[] arr = new char[1];
        while (byteLength > 0) {
            c = str.charAt(index);
            arr[0] = c;
            if (!isChineseString(new String(arr))) {
                byteLength--;
            } else {
                byteLength--;
                byteLength--;
            }

            buff.append(c);
            index++;
        }

        buff.append("...");

        return buff.toString();
    }
    /**
     * 检查给定的字符串中是否包含中文信息
     * 
     * @param string
     * @return
     */
    public static boolean isChineseString(String string) {
        if (isBlank(string))
            return false;

        String patternRange = "[\\u4E00-\\u9FA5]+";
        Pattern pattern = Pattern.compile(patternRange);
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    /**
     * 将String中Null转换成""
     * 
     * @param string
     * @return
     */
    public static String nullToString(String string) {

        return StringUtil.isBlank(string) ? "" : string;
    }

    public static String trim0(String num) {
        int begin = 0;
        int end = num.length() - 1;

        while (num.charAt(end) == '0')
            --end;

        if (num.charAt(end) == '.')
            --end;

        return begin <= end ? num.substring(begin, end + 1) : "0";
    }

    public static String trimStr(String str) {

        return trimToEmpty(str).replaceAll("[\\r\\n]", "");
    }

    /**
     * 检测字符串是否不为空(null,"","null")
     * 
     * @param s
     * @return 不为空则返回true，否则返回false
     */
    public static boolean notEmpty(String s) {
        return s != null && !"".equals(s) && !"null".equals(s);
    }

    public static boolean notEmpty(Object obj) {
        if (obj instanceof String)
            return notEmpty((String) obj);
        return obj != null;
    }

    /**
     * 检测字符串是否为空(null,"","null")
     * 
     * @param s
     * @return 为空则返回true，不否则返回false
     */
    public static boolean isEmpty(String s) {
        return s == null || "".equals(s) || "null".equals(s);
    }

    /**
     * 属性copy 将一个A对象的值copy到另外一个B对象，当A对象的属性值为null时则使用B对象的属性值 copyProperties
     * 
     * @author gwb
     * @param from
     * @param to
     *            2015年10月9日 上午10:32:13
     */
    public static void copyProperties(Object from, Object to) throws Exception {
        copyPropertiesWithFilter(from, to, StringUtil.class, "notEmpty");
    }

    /**
     * 属性copy 将一个A对象的值copy到另外一个B对象，当A对象的属性值为null(不包括"")时则使用B对象的属性值
     * copyProperties
     * 
     * @author guojianbin
     * @param from
     * @param to
     * 
     */
    public static void copyPropertiesAllowEmpty(Object from, Object to) throws Exception {
        copyPropertiesWithFilter(from, to, StringUtil.class, "notNull");
    }

    private static void copyPropertiesWithFilter(Object from, Object to, Class<?> filter, String filterMethod) throws Exception {
        if (from == null || to == null)
            throw new Exception("copy的对象不能为空");
        if (!from.getClass().getName().equals(to.getClass().getName()))
            throw new Exception("同一个对象无法copy");
        try {
            Method method = filter.getMethod(filterMethod, Object.class);
            for (Class<?> clazz = from.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object value = field.get(from);
                    if ((Boolean) method.invoke(null, value)) {
                        field.set(to, value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean notNull(Object obj) {
        return obj != null;
    }

    public static String imagePath(String path, Integer width, Integer height) {
        if (path == null || path.length() == 0)
            return path;
        int pointPostion = path.indexOf(".");
        if (pointPostion == -1)
            return path;
        String pre = path.substring(0, pointPostion);
        String suf = path.substring(pointPostion, path.length());
        StringBuffer buf = new StringBuffer();
        buf.append(pre);
        buf.append("_");
        buf.append(width);
        buf.append("x");
        buf.append(height);
        buf.append(suf);
        return buf.toString();
    }


    /**
     * 
     * @Description 格式化，无小数点
     * @param num
     * @return
     * @author zhaoyonglian
     * @date 2015年12月3日 下午4:32:34
     */
    public static String format(String num) {
        int index = num.indexOf(".");
        num = num.substring(0, index);
        return num;
    }

    public static String format(Double number) {
        String num = String.valueOf(number);
        int index = num.indexOf(".");
        num = num.substring(0, index);
        return num;
    }

    public static String formatUndefined(String str) {
        if (str == "undefined") {
            str = "";
        }
        return str;
    }
    
    /**
     * 
     * @Description 根据分隔符把字符串转list
     * @param str 原字符串
     * @param separator 分隔符
     * @return
     * @author lipeng
     * @date 2015年5月30日 
     */
    public static List<String> splitToList(String str,String separator) {
    	List<String> list = new ArrayList<String>();
    	
        if (!notEmpty(trimStr(str))) {
        	return null;
        }
        
        String[] arr = str.split(separator);
        for (String s : arr) {
        	list.add(s);
		}
        
        return list;
    }

    /**
     * 反转逗号拼接的字符串
     * @param str
     * @return
     */
    public static String reverseString(String str){
        StringBuffer sb = new StringBuffer();
        String[] strArr = str.split(",");
        for(int i=strArr.length-1;i>=0;i--){
            sb.append(strArr[i]+",");
        }
        if(sb.length()>0){
            return sb.substring(0,sb.length()-1);
        }
        return sb.toString();
    }

    /**
     * 生成两位数code 编码规则[A-Z]+[0-9] 或者[0-9]+A-Z] 组成两位数的唯一编码
     * @param preCode 上一编码
     * @return
     */
    public static String generateTowCode(String preCode){
        int c1 = 65 ; // 默认为A
        int c2 = 48 ; // 默认为0
        if(preCode==null){
            String code = (char) c1+String.valueOf((char)c2);
            return code ;
        }
        c1 = preCode.charAt(0);
        c2 = preCode.charAt(1);
        c2++ ;
        if(c2==58){
            c1++;
            c2=48; // 归0
            if(c1==91){
                c1=48;  // 转换为0
                c2=65;  // 转换为A
            }
        }else if(c2==91){
            c1++;
            c2=65; // 转换为A
        }
        return (char) c1+String.valueOf((char)c2);
    }

    public static String encode(String str) {

        String strEncode = "";

        try {
            strEncode = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
        }

        return strEncode;
    }

    public static String decode(String str) {

        String strDecode = "";

        try {
            if (str != null)
                strDecode = URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
        }

        return strDecode;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
    }

    public static String generateCode() {
        return System.currentTimeMillis()+RandomUtil.randomStr(3);
    }
}
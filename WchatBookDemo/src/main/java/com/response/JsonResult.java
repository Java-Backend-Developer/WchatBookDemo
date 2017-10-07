/**
 * Copyright 2014-2015 goujia.com
 * All rights reserved.
 * 
 * @project
 * @author guojianbin
 * @version 3.0
 * @date 2015-07-14
 */
package com.response;

/**
 * 统一格式的Json返回值
 */
public class JsonResult<T> {

	/**
	 * 返回编码：<br>
	 *     success： "0"<br>
	 *     failure: error code
	 */
	protected String ret;
	
	/**
	 * 返回提示信息：<br>
	 *     success： "success"<br>
	 *     failure: error massage
	 */
	protected String msg;
	
	/**
	 * 返回结果数据：<br>
	 *     success： 需返回的数据<br>
	 *     failure: null
	 */
	protected T data;

	public JsonResult() {
		this.ret = "0";
		this.msg = "success";
	}
	
	public JsonResult(T data) {
		this.ret = "0";
		this.msg = "success";
		this.data = data;
	}
	
	public JsonResult(String ret, String msg, T data) {
        this.ret = ret;
        this.msg = msg;
        this.data = data;
    }

    public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}

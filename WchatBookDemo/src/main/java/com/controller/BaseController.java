package com.controller;

import com.response.JsonResult;

/**
 * @author zijiao
 * @version 17/10/9
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseController {

    @SuppressWarnings("unchecked")
    protected <T> JsonResult<T> error(String msg, Object data) {
        return new JsonResult<T>("-1", msg, (T) data);
    }

    protected <T> JsonResult<T> error(String msg) {
        return error(msg, null);
    }

    protected <T> JsonResult<T> error() {
        return error("FAIL");
    }

    @SuppressWarnings("unchecked")
    protected <T> JsonResult<T> success(String msg, Object data) {
        return new JsonResult<T>("0", msg, (T) data);
    }


    protected <T> JsonResult<T> success(Object data) {
        return success("SUCCESS", data);
    }

}

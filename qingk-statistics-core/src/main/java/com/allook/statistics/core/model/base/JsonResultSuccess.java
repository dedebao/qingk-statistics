package com.allook.statistics.core.model.base;

import java.io.Serializable;

import com.allook.statistics.core.model.JsonResult;

/**
 * Json返回成功结果
 *
 * @since v1.0.0
 * @author zhuguoxiao<br>
 *         Created on 2018年6月22日
 */
public final class JsonResultSuccess<T> implements JsonResult, Serializable {
    private static final long serialVersionUID = 1L;
    private Boolean success;
    private String message;
    private Integer code;
    private Long timestamp;
    private T result;

    public JsonResultSuccess() {
        this(true, 200, null, null);
    }

    public JsonResultSuccess(T data) {
        this(true, 200, null, data);
    }

    public JsonResultSuccess(String message, T data) {
        this(true, 200, message, data);
    }

    private JsonResultSuccess(boolean success, int code, String message, T result) {
        this.success = success ? Boolean.TRUE : Boolean.FALSE;
        this.message = message;
        this.code = code;
        this.timestamp = System.currentTimeMillis();
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public T getResult() {
        return result;
    }

}

package com.allook.statistics.core.model.base;

import java.io.Serializable;

import com.allook.statistics.core.model.JsonResult;
import com.allook.statistics.core.model.JsonResult.ResponseCode;

/**
 * Json返回失败结果
 *
 * @since v1.0.0
 * @author zhuguoxiao<br>
 *         Created on 2018年6月22日
 */
public class JsonResultFailure implements JsonResult, Serializable {
    private static final long serialVersionUID = 1L;
    private Boolean success;
    private String message;
    private int code;
    private Long timestamp;

    public JsonResultFailure() {
        this(ResponseCode.UNKNOWN_ERROR);
    }

    public JsonResultFailure(ResponseCode responseCode) {
        this(false, responseCode.getCode(), responseCode.getMessage());
    }

    private JsonResultFailure(boolean success, int code, String message) {
        this.success = success ? Boolean.TRUE : Boolean.FALSE;
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Long getTimestamp() {
        return timestamp;
    }

}

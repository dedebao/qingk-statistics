package com.allook.statistics.core.model;

import com.alibaba.fastjson.JSON;
import com.allook.statistics.core.model.base.JsonResultFailure;
import com.allook.statistics.core.model.base.JsonResultSuccess;

/**
 * 
 * Json返回结果
 *
 * @since v1.0.0
 * @author zhuguoxiao<br>
 *         Created on 2018年6月22日
 */
public interface JsonResult {

    /**
     * Json返回结果错误类型
     *
     * @since v1.0.0
     * @author zhuguoxiao<br>
     *         Created on 2018年6月22日
     */
    public enum ResponseCode {
        // @formatter:off
        UNKNOWN_ERROR(-1, "未知错误"),
        
        BAD_REQUEST_ERROR(400, "请求参数错误"),
        UNAUTHORIZED_ERROR(401, "尚未登录"),
        FORBIDDEN_ERROR(403, "无访问权限"),

        INTERNAL_SERVER_ERROR(500, "内部服务器错误"),
        
        USERNAME_PASSWORD_ERROR(600, "用户名或密码错误"),
        LOGIN_TIME_EXCEED_UPLIMIT_ERROR(601, "登录失败次数已达上限，请稍后重试"),
        ACCOUNT_DISABLED_ERROR(602, "账户被禁用，请联系管理员");
        // @formatter:on

        private int code;
        private String message;

        private ResponseCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

    }

    /**
     * 生成Json成功结果
     * <p>
     * 示例：<code>{"result":true}</code>
     *
     * @param <T>
     *            业务数据类型
     * @return Json成功结果
     * @since v1.0.0
     */
    static <T> JsonResult success() {
        return new JsonResultSuccess<T>();
    }

    /**
     * 生成Json成功结果
     * <p>
     * 示例一：<code>{"data":{},"result":true}</code><br>
     * 示例二：<code>{"data":[],"result":true}</code>
     *
     * @param bean
     *            业务数据
     * @param <T>
     *            业务数据类型
     * @return Json成功结果
     * @since v1.0.0
     */
    static <T> JsonResult success(T bean) {
        return new JsonResultSuccess<T>(bean);
    }

    /**
     * 生成Json成功结果
     * <p>
     * 示例一：<code>{"message":"修改成功","result":true}</code><br>
     * 示例二：<code>{"message":"查询成功","data":{},"result":true}</code><br>
     * 示例三：<code>{"message":"查询成功","data":[],"result":true}</code>
     *
     * @param message
     *            提示信息
     * @param bean
     *            业务数据
     * @param <T>
     *            业务数据类型
     * @return Json成功结果
     * @since v1.0.0
     */
    static <T> JsonResult success(String message, T bean) {
        return new JsonResultSuccess<T>(message, bean);
    }

    /**
     * 生成Json未知错误结果
     * <p>
     * 示例：<code>{"code":999,"message":"未知错误","result":false}</code>
     *
     * @return Json未知错误结果
     * @since v1.0.0
     */
    static JsonResult error() {
        return new JsonResultFailure();
    }

    /**
     * 生成Json错误结果
     * <p>
     * 示例：<code>{"code":101,"message":"用户名或密码错误","result":false}</code>
     *
     * @param responseCode
     *            错误类型
     * @return Json错误结果
     * @since v1.0.0
     */
    static JsonResult error(ResponseCode responseCode) {
        return new JsonResultFailure(responseCode);
    }

    /**
     * 获取json字符串
     *
     * @return json字符串
     * @since v1.0.0
     */
    default String toJson() {
        return JSON.toJSONString(this);
    }

}

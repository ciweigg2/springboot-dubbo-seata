package com.mxc.love.web.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Ciwei
 * @Date 2019/5/11/011
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> implements Serializable {

    /**
     * 如果是成功，则code为200
     */
    private int code;

    /**
     * 对错误的具体解释
     */
    private String message;

    /**
     * 返回的结果包装在value中，value可以是单个对象
     */
    private Object value;

    public static <T> ApiResult<T> success(T t) {
        return new ApiResult(200, "成功", t);
    }

    public static <T> ApiResult<T> failed(T t) {
        return new ApiResult(500, "失败", t);
    }

}
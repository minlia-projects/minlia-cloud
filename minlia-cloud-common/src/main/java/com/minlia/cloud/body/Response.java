package com.minlia.cloud.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.cloud.utils.RequestIdGenerator;
import lombok.Data;

@Data
public class Response<T> implements Body {

    public static final String SUCCESS_MESSAGE = "OK";
    public static final String FAILURE_MESSAGE = "Failure";

    public static final Integer SUCCESS = 1;
    public static final Integer FAILURE = 0;

    /**
     * 用于兼容http状态码, 一般在业务上不使用
     */
    protected Integer status;

    /**
     * 业务操作完成后的返回代码
     */
    protected Integer code;

    /**
     * 业务操作完成后的返回信息
     */
    protected String message;

    /**
     * 携带的负载返回对象
     */
    protected T payload;

    /**
     * 当前操作时间
     */
    protected Long timestamp;

    /**
     * 请求ID
     */
    @JsonProperty
    protected String requestId = RequestIdGenerator.generateRequestId();

    private Response(Integer status, Integer code, String message, T payload){
        this.status = status;
        this.code = code;
        this.message = message;
        this.payload = payload;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> Response<T> is(boolean bool) {
        return new Response(bool?SUCCESS:FAILURE, bool?SUCCESS:FAILURE, bool?SUCCESS_MESSAGE:FAILURE_MESSAGE, null);
    }
    public static <T> Response<T> is(boolean bool, T payload) {
        return new Response(bool?SUCCESS:FAILURE, bool?SUCCESS:FAILURE, bool?SUCCESS_MESSAGE:FAILURE_MESSAGE, payload);
    }
    public static <T> Response<T> is(boolean bool, String message) {
        return new Response(bool?SUCCESS:FAILURE, bool?SUCCESS:FAILURE, message, null);
    }
    public static <T> Response<T> is(boolean bool, String message, T payload) {
        return new Response(bool?SUCCESS:FAILURE, bool?SUCCESS:FAILURE, message, payload);
    }

    public static <T> Response<T> success() {
        return new Response(SUCCESS, SUCCESS, SUCCESS_MESSAGE, null);
    }
    public static <T> Response<T> success(T payload) {
        return new Response(SUCCESS, SUCCESS, SUCCESS_MESSAGE, payload);
    }
    public static <T> Response<T> success(String message) {
        return new Response(SUCCESS, SUCCESS, message, null);
    }
    public static <T> Response<T> success(String message, T payload) {
        return new Response(SUCCESS, SUCCESS, message, payload);
    }
    public static <T> Response<T> success(Integer code, String message) {
        return new Response(SUCCESS, code, message, null);
    }
    public static <T> Response<T> success(Integer code, String message, T payload) {
        return new Response(SUCCESS, code, message, payload);
    }

    public static <T> Response<T> failure() {
        return new Response(FAILURE, FAILURE, FAILURE_MESSAGE, null);
    }
    public static <T> Response<T> failure(T payload) {
        return new Response(FAILURE, FAILURE, FAILURE_MESSAGE, payload);
    }
    public static <T> Response<T> failure(String message) {
        return new Response(FAILURE, FAILURE, message, null);
    }
    public static <T> Response<T> failure(String message, T payload) {
        return new Response(FAILURE, FAILURE, message, payload);
    }
    public static <T> Response<T> failure(Integer code, String message) {
        return new Response(FAILURE, code, message, null);
    }
    public static <T> Response<T> failure(Integer code, String message, T payload) {
        return new Response(FAILURE, code, message, payload);
    }

    public boolean isSuccess() {
        return this.status.equals(SUCCESS);
    }

}
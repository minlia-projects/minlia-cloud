package com.minlia.cloud.body;

import com.minlia.cloud.code.Code;
import com.minlia.cloud.code.SystemCode;
import com.minlia.cloud.utils.RequestIdGenerator;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 状态化的返回体
 * @param <T>
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Body {

    public static final Integer STATUS_SUCCESS = HttpStatus.OK.value();

    public static final Integer STATUS_FAILURE = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public static final String CODE_SUCCESS = "SUCCESS";

    public static final String CODE_FAILURE = "FAILURE";

    /**
     * 用于兼容http状态码, 一般在业务上不使用
     */
    protected Integer status;

    /**
     * 业务操作完成后的返回代码
     */
    protected String code;

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
    protected String requestId = RequestIdGenerator.generateRequestId();

    public Response(){
        this.timestamp = System.currentTimeMillis();
    }

    public Response(String code, String message){
        this.status = STATUS_SUCCESS;
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public Response(String code, String message, T payload){
        this.status = STATUS_SUCCESS;
        this.code = code;
        this.message = message;
        this.payload = payload;
        this.timestamp = System.currentTimeMillis();
    }

    public Response(Integer status, String code, String message){
        this.status = null == status ? STATUS_SUCCESS : status;
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public Response(Integer status, String code, String message, T payload){
        this.status = null == status ? STATUS_SUCCESS : status;
        this.code = code;
        this.message = message;
        this.payload = payload;
        this.timestamp = System.currentTimeMillis();
    }


    public static Response is(boolean bool) {
        return is(bool, null);
    }
    public static <T> Response<T> is(boolean bool, T payload) {
        if (bool) {
            return new Response(SystemCode.Message.SUCCESS.name(), SystemCode.Message.SUCCESS.message(), payload);
        } else {
            return new Response(SystemCode.Message.FAILURE.name(), SystemCode.Message.FAILURE.message(), payload);
        }
    }
    public static Response is(boolean bool, Code code) {
        return is(bool, code.code(), code.message());
    }
    public static <T> Response is(boolean bool, Code code, T payload) {
        return is(bool, code.code(), code.message(), payload);
    }
    public static Response is(boolean bool, String code, String message) {
        return is(bool, code, message, null);
    }
    public static <T> Response<T> is(boolean bool, String code, String message, T payload) {
        if (bool) {
            return success(code, message, payload);
        } else {
            return failure(code, message, payload);
        }
    }


    public static Response success() {
        return success(SystemCode.Message.SUCCESS);
    }
    public static Response success(Code code) {
        return success(code, null);
    }
    public static Response success(String code, String message) {
        return success(code, message, null);
    }
    public static <T> Response success(T payload) {
        return success(SystemCode.Message.SUCCESS, payload);
    }
    public static <T> Response success(Code code, T payload) {
        return success(code.code(), code.message(), payload);
    }
    public static <T> Response success(String code, String message, T payload) {
        return new Response(code, message, payload);
    }
    public static Response successMsg(String message) {
        return new Response(SystemCode.Message.SUCCESS.code(), message);
    }


    public static Response failure() {
        return failure(SystemCode.Message.FAILURE);
    }
    public static Response failure(Code code) {
        return failure(code, null);
    }
    public static Response failure(String code, String message) {
        return failure(code, message, null);
    }
    public static <T> Response failure(T payload) {
        return failure(SystemCode.Message.FAILURE, payload);
    }
    public static <T> Response failure(Code code, T payload) {
        return failure(code.code(), code.message(), payload);
    }
    public static <T> Response failure(String code, String message, T payload) {
        return new Response(code, message, payload);
    }
    public static Response failureMsg(String message) {
        return new Response(SystemCode.Message.FAILURE.code(), message);
    }

    public boolean isSuccess() {
        return this.status.equals(STATUS_SUCCESS);
    }

}
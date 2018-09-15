//package com.minlia.cloud.body;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.minlia.cloud.utils.RequestIdGenerator;
//import lombok.Data;
//import org.apache.commons.lang3.builder.EqualsBuilder;
//import org.apache.commons.lang3.builder.HashCodeBuilder;
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.springframework.http.HttpStatus;
//
//import java.util.Date;
//
///**
// * 状态化的返回体
// * @param <T>
// */
//@Data
//
//public class StatefulBody<T> implements Body {
//
//    public static final String SUCCESS_MESSAGE = "OK";
//    public static final String FAILURE_MESSAGE = "Failure";
//
//    public static final Integer SUCCESS = 1;
//    public static final Integer FAILURE = 0;
//
//    /**
//     * 用于兼容http状态码, 一般在业务上不使用
//     */
//    @JsonProperty
//    protected Integer status;
//
//    /**
//     * 业务操作完成后的返回代码
//     */
//    @JsonProperty
//    protected Integer code;
//
//    /**
//     * 业务操作完成后的返回信息
//     */
//    @JsonProperty
//    protected String message;
//
//    /**
//     * 携带的负载返回对象
//     */
//    @JsonProperty
//    protected T payload;
//
//    /**
//     * 当前操作时间
//     */
//    @JsonProperty
//    protected Date timestamp;
//
//    /**
//     * 请求ID
//     */
//    @JsonProperty
//    protected String requestId= RequestIdGenerator.generateRequestId();
//
//    public StatefulBody() {
//        this.status = SUCCESS;
//        this.code = HttpStatus.OK.value();
//        this.message = FAILURE_MESSAGE;
//        this.payload=null;
//        this.timestamp = new Date();
//    }
//
//    public StatefulBody(Integer status,Integer code,String message) {
//        this.status = status;
//        this.code = code;
//        this.message = message;
//        this.payload=null;
//        this.timestamp = new Date();
//    }
//
//    public StatefulBody(Integer status,Integer code,String message,T payload) {
//        this.status = status;
//        this.code = code;
//        this.message = message;
//        this.payload = payload;
//        this.timestamp = new Date();
//    }
//
//    public Date getTimestamp() {
//        return timestamp;
//    }
//
//    public Integer getCode() {
//        return code;
//    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public T getPayload() {
//        return payload;
//    }
//
//    public void setPayload(T payload) {
//        this.payload = payload;
//    }
//
//    public boolean isSuccess() {
//        return null != status && status.equals(SUCCESS);
//    }
//
//    @Override
//    public String toString() {
//       return ToStringBuilder.reflectionToString(this);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        return EqualsBuilder.reflectionEquals(this,o);
//    }
//
//    @Override
//    public int hashCode() {
//        return HashCodeBuilder.reflectionHashCode(this);
//    }
//
//}

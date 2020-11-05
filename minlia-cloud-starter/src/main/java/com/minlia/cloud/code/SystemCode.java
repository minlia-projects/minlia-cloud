package com.minlia.cloud.code;

/**
 * @author garen
 * @date 2018/9/14
 */
public class SystemCode {

    final static String CODE_PREFIX = "system.common";

    public enum Exception implements Code {

        /**
         * 403，API无权限
         */
        FORBIDDEN,

        /**
         * 404，API不存在
         */
        API_NOT_NULL,

        /**
         * 不支持的请求方法
         */
        UNSUPPORTED_REQUEST_METHOD,

        /**
         * 远程请求失败
         */
        REMOTE_REQUEST_FAILURE,

        /**
         * INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
         */
        INTERNAL_SERVER_ERROR,

        /**
         * 方法请求参数校验失败
         * MethodArgumentNotValidException
         */
        BAD_REQUEST;

        @Override
        public String module() {
            return CODE_PREFIX;
        }

    }

    public enum Message implements Code {

        //数据不存在
        DATA_NOT_EXISTS,

        //数据已存在
        DATA_ALREADY_EXISTS,

        //操作成功
        SUCCESS,

        //操作失败
        FAILURE,

        CREATE_SUCCESS,

        CREATE_FAILURE,

        UPDATE_SUCCESS,

        UPDATE_FAILURE,

        DELETE_SUCCESS,

        DELETE_FAILURE,

        SAVE_SUCCESS,

        SAVE_FAILURE,

        ALREADY_USED,

        ALREADY_CANCELED,

        ALREADY_DELETED,

        ALREADY_ACCEPTED,

        ALREADY_FINISHED;

        @Override
        public String module() {
            return CODE_PREFIX;
        }

    }

}
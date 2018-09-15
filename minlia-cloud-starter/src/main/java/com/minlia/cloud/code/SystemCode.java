package com.minlia.cloud.code;

import com.minlia.cloud.i18n.Lang;

/**
 * Created by garen on 2018/9/14.
 */
public class SystemCode {

    public enum Exception implements Code {

        /**
         * 403，API不存在
         */
        NOT_AUTHORIZED(100000,"system.common.exception.100000"),

        API_NOT_NULL(100001,"system.common.exception.100001"),

        /**
         * 不能为空
         */
        NOT_NULL(100001,"system.common.exception.100001"),

        /**
         * 数据不存在
         */
        DATA_NON_EXISTS(100002,"system.common.exception.100002"),

        /**
         * 数据已存在
         */
        DATA_ALREADY_EXISTS(100003,"system.common.exception.100001"),

        /**
         * 不支持的请求方法
         */
        UNSUPPORTED_REQUEST_METHOD(100005,"system.common.exception.100005"),

        /**
         * 远程请求失败
         */
        REMOTE_REQUEST_FAILURE(100006,"system.common.exception.100006");

        private int code;
        private String i18nKey;

        Exception(int code, String i18nKey) {
            this.code = code;
            this.i18nKey = i18nKey;
        }

        @Override
        public int code() {
            return code;
        }

        @Override
        public String i18nKey() {
            return i18nKey;
        }

        @Override
        public String message(){
            return Lang.get(this.i18nKey);
        }

    }

    public enum Message implements Code {

        /**
         * 操作成功
         */
        SUCCESS(100001,"system.common.message.100001"),

        /**
         * 操作失败
         */
        FAILURE(100002,"system.common.message.100002"),

        CREATE_SUCCESS(100003,"system.common.message.100003"),

        CREATE_FAILURE(100004,"system.common.message.100004"),

        UPDATE_SUCCESS(100005,"system.common.message.100005"),

        UPDATE_FAILURE(100006,"system.common.message.100006"),

        DELETE_SUCCESS(100007,"system.common.message.100007"),

        DELETE_FAILURE(100008,"system.common.message.100008"),

        SAVE_SUCCESS(100010,"system.common.message.100010"),

        SAVE_FAILURE(100011,"system.common.message.100011");

        private int code;
        private String i18nKey;

        Message(int code, String i18nKey) {
            this.code = code;
            this.i18nKey = i18nKey;
        }

        @Override
        public int code() {
            return code;
        }

        @Override
        public String i18nKey() {
            return i18nKey;
        }

        @Override
        public String message(){
            return Lang.get(this.i18nKey);
        }

    }

}

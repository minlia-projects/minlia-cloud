package com.minlia.cloud.code;

import com.minlia.cloud.i18n.Lang;

/**
 * Created by garen on 2018/9/14.
 */
public class SystemCode {

    public enum Exception implements Code {

        /**
         * 403，API无权限
         */
        NOT_AUTHORIZED(100000,"system.common.exception.100000"),

        /**
         * 404，API不存在
         */
        API_NOT_NULL(100001,"system.common.exception.100001"),

        /**
         * 不支持的请求方法
         */
        UNSUPPORTED_REQUEST_METHOD(100002,"system.common.exception.100002"),

        /**
         * 远程请求失败
         */
        REMOTE_REQUEST_FAILURE(100003,"system.common.exception.100003");

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

        DATA_NOT_EXISTS(100000,"system.common.message.100000"),

        DATA_ALREADY_EXISTS(100001,"system.common.message.100001"),

        SUCCESS(100002,"system.common.message.100002"),

        FAILURE(100003,"system.common.message.100003"),

        CREATE_SUCCESS(100004,"system.common.message.100004"),

        CREATE_FAILURE(100005,"system.common.message.100005"),

        UPDATE_SUCCESS(100006,"system.common.message.100006"),

        UPDATE_FAILURE(100007,"system.common.message.100007"),

        DELETE_SUCCESS(100008,"system.common.message.100008"),

        DELETE_FAILURE(100009,"system.common.message.100009"),

        SAVE_SUCCESS(100010,"system.common.message.100010"),

        SAVE_FAILURE(100011,"system.common.message.100011"),

        ALREADY_USED(100012,"system.common.message.100012"),

        ALREADY_CANCELED(100013,"system.common.message.100013"),

        ALREADY_DELETED(100014,"system.common.message.100014"),

        ALREADY_ACCEPTED(100015,"system.common.message.100015"),

        ALREADY_FINISHED(100016,"system.common.message.100016");

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

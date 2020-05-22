package com.minlia.cloud.code;

import com.google.common.base.CaseFormat;
import com.minlia.cloud.i18n.Lang;

import java.util.StringJoiner;

/**
 * Created by garen on 2018/9/14.
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
        public String code() {
            return this.name();
        }

        @Override
        public String i18nKey() {
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE, new StringJoiner(".").add(CODE_PREFIX).add(this.getClass().getSimpleName()).add(this.name()).toString());
        }

        @Override
<<<<<<< HEAD
        public String message(Object ... var){
            return Lang.get(this.i18nKey(), var);
=======
        public String message(Object... args) {
            return Lang.get(this.i18nKey(), args);
>>>>>>> master
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
        public String code() {
            return this.name();
        }

        @Override
        public String i18nKey() {
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE, new StringJoiner(".").add(CODE_PREFIX).add(this.getClass().getSimpleName()).add(this.name()).toString());
        }

        @Override
<<<<<<< HEAD
        public String message(Object ... var){
            return Lang.get(this.i18nKey(), var);
=======
        public String message(Object... args) {
            return Lang.get(this.i18nKey(), args);
>>>>>>> master
        }

    }

}

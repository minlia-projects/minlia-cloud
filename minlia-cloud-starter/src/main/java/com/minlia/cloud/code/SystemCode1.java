//package com.minlia.cloud.code;
//
//import com.google.common.base.CaseFormat;
//import com.minlia.cloud.i18n.Lang;
//
//import java.util.StringJoiner;
//
///**
// * Created by garen on 2018/9/14.
// */
//public class SystemCode1 {
//
//    static final String CODE_PREFIX = "system.common";
//
//    public enum Exception implements Code {
//
//        /**
//         * 400
//         * 1、语义有误，当前请求无法被服务器理解。除非进行修改，否则客户端不应该重复提交这个请求。
//         * 2、请求参数有误。
//         */
//        BAD_REQUEST,
//
//        /**
//         * 401  未登陆、无访问凭证,当前请求需要用户验证
//         */
//        UNAUTHORIZED,
//
//        /**
//         * 402  该状态码是为了将来可能的需求而预留的。
//         */
//        PAYMENT_REQUIRED,
//
//        /**
//         * 403  无资源权限，服务器已经理解请求，但是拒绝执行它
//         */
//        FORBIDDEN,
//
//        /**
//         * 404  资源不存在
//         */
//        NOT_FOUND,
//
//        /**
//         * 500  Internal Server Error
//         */
//        INTERNAL_SERVER_ERROR,
//
//        /**
//         * 远程请求失败
//         */
//        REMOTE_REQUEST_FAILURE;
//
//        @Override
//        public String code() {
//            return this.name();
//        }
//
//        @Override
//        public String i18nKey() {
//            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE, (new StringJoiner(".")).add(CODE_PREFIX).add(this.getClass().getSimpleName()).add(this.name()).toString());
//        }
//
//        @Override
//        public String message(){
//            return Lang.get(this.i18nKey());
//        }
//
//    }
//
//    public enum Message implements Code {
//
//        //数据不存在
//        DATA_NOT_EXISTS(100000,"system.common.message.100000"),
//
//        //数据已存在
//        DATA_ALREADY_EXISTS(100001,"system.common.message.100001"),
//
//        //操作成功
//        SUCCESS(100002,"system.common.message.100002"),
//
//        //操作失败
//        FAILURE(100003,"system.common.message.100003"),
//
//        CREATE_SUCCESS(100004,"system.common.message.100004"),
//
//        CREATE_FAILURE(100005,"system.common.message.100005"),
//
//        UPDATE_SUCCESS(100006,"system.common.message.100006"),
//
//        UPDATE_FAILURE(100007,"system.common.message.100007"),
//
//        DELETE_SUCCESS(100008,"system.common.message.100008"),
//
//        DELETE_FAILURE(100009,"system.common.message.100009"),
//
//        SAVE_SUCCESS(100010,"system.common.message.100010"),
//
//        SAVE_FAILURE(100011,"system.common.message.100011"),
//
//
//
//        ALREADY_USED(100012,"system.common.message.100012"),
//
//        ALREADY_CANCELED(100013,"system.common.message.100013"),
//
//        ALREADY_DELETED(100014,"system.common.message.100014"),
//
//        ALREADY_ACCEPTED(100015,"system.common.message.100015"),
//
//        ALREADY_FINISHED(100016,"system.common.message.100016");
//
//        private int code;
//        private String i18nKey;
//
//        Message(int code, String i18nKey) {
//            this.code = code;
//            this.i18nKey = i18nKey;
//        }
//
//        @Override
//        public String code() {
//            return code;
//        }
//
//        @Override
//        public String i18nKey() {
//            return i18nKey;
//        }
//
//        @Override
//        public String message(){
//            return Lang.get(this.i18nKey);
//        }
//
//    }
//
//}

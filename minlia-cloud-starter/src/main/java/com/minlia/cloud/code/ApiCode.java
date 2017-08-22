package com.minlia.cloud.code;

/**
 * Created by will on 6/19/17.
 * API 业务返回码定义
 */
public abstract class ApiCode {

    /**
     * 基数
     */
    public static final int BASED_ON = 10000;


    /**
     * 资源找不到
     */
    public static final int NOT_FOUND = BASED_ON + 30004;


    /**
     * 未登录
     */
    public static final int NOT_LOGIN = BASED_ON + 30001;

    /**
     * 无权限访问
     */
    public static final int NOT_AUTHORIZED = BASED_ON + 30003;


    /**
     * 不能为空
     */
    public static final int NOT_NULL = BASED_ON + 40001;

    /**
     * 无效验证码
     */
    public static final int INVALID_SECURITY_CODE = BASED_ON + 40002;


    /**
     * 无效用户名或密码
     */
    public static final int INVALID_CREDENTIAL = BASED_ON + 40003;


    /**
     * 账户已禁用
     */
    public static final int ACCOUNT_DISABLED = BASED_ON + 40004;

    /**
     * 登录失败
     */
    public static final int LOGIN_FAILED = BASED_ON + 40005;
    /**
     * 无效用户
     */
    public static final int INVALID_USER = BASED_ON + 40006;

    /**
     * 无效的原密码
     */
    public static final int INVALID_RAW_PASSWORD = BASED_ON + 40007;

    /**
     * 还有子项无法删除
     */
    public static final int COULD_NOT_DELETE_HAS_CHILDREN = BASED_ON + 40008;

    /**
     * 访问令牌已过期
     */
    public static final int ACCESS_TOKEN_HAS_EXPIRED = BASED_ON + 40009;

    /**
     * 验证失败
     */
    public static final int AUTHENTICATION_FAILED = BASED_ON + 40010;

    /**
     * 数据已存在
     */
    public static final int DATA_ALREADY_EXISTS = BASED_ON + 40011;



    public ApiCode() {
        throw new AssertionError();
    }

}

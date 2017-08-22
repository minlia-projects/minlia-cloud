package com.minlia.cloud.code;

/**
 * Created by will on 6/19/17.
 * API 业务返回码定义
 */
public abstract class ApiCode {

    /**
     * 基数
     */
    public static final int CARDINAL = 10000;


    /**
     * 资源找不到
     */
    public static final int NOT_FOUND = CARDINAL + 30004;


    /**
     * 未登录
     */
    public static final int NOT_LOGIN = CARDINAL + 30001;

    /**
     * 无权限访问
     */
    public static final int NOT_AUTHORIZED = CARDINAL + 30003;


    /**
     * 不能为空
     */
    public static final int NOT_NULL = CARDINAL + 40001;

    /**
     * 无效验证码
     */
    public static final int INVALID_SECURITY_CODE = CARDINAL + 40002;


    /**
     * 无效用户名或密码
     */
    public static final int INVALID_CREDENTIAL = CARDINAL + 40003;


    /**
     * 账户已禁用
     */
    public static final int ACCOUNT_DISABLED = CARDINAL + 40004;

    /**
     * 登录失败
     */
    public static final int LOGIN_FAILED = CARDINAL + 40005;
    /**
     * 无效用户
     */
    public static final int INVALID_USER = CARDINAL + 40006;

    /**
     * 无效的原密码
     */
    public static final int INVALID_RAW_PASSWORD = CARDINAL + 40007;

    /**
     * 还有子项无法删除
     */
    public static final int COULD_NOT_DELETE_HAS_CHILDREN = CARDINAL + 40008;

    /**
     * 访问令牌已过期
     */
    public static final int ACCESS_TOKEN_HAS_EXPIRED = CARDINAL + 40009;

    /**
     * 验证失败
     */
    public static final int AUTHENTICATION_FAILED = CARDINAL + 40010;

    /**
     * 数据已存在
     */
    public static final int DATA_ALREADY_EXISTS = CARDINAL + 40011;



    public ApiCode() {
        throw new AssertionError();
    }

}

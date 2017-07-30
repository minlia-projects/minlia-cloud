package com.minlia.cloud.code;

/**
 * Created by will on 6/19/17.
 * API 业务返回码定义
 */
public abstract class ApiCode {

    /**
     * 基数
     */
    public static final Integer BASED = 10000;


    /**
     * 资源找不到
     */
    public static final Integer NOT_FOUND = BASED + 30004;


    /**
     * 未登录
     */
    public static final Integer NOT_LOGIN = BASED + 30001;

    /**
     * 无权限访问
     */
    public static final Integer NOT_AUTHORIZED = BASED + 30003;


    /**
     * 不能为空
     */
    public static final Integer NOT_NULL = BASED + 40001;

    /**
     * 无效验证码
     */
    public static final Integer INVALID_SECURITY_CODE = BASED + 40002;


    /**
     * 无效用户名或密码
     */
    public static final Integer INVALID_CREDENTIAL = BASED + 40003;


    /**
     * 账户已禁用
     */
    public static final Integer ACCOUNT_DISABLED = BASED + 40004;

    /**
     * 登录失败
     */
    public static final Integer LOGIN_FAILED = BASED + 40005;
    /**
     * 无效用户
     */
    public static final Integer INVALID_USER = BASED + 40006;

    /**
     * 无效的原密码
     */
    public static final Integer INVALID_RAW_PASSWORD = BASED + 40007;

    /**
     * 还有子项无法删除
     */
    public static final Integer COULD_NOT_DELETE_HAS_CHILDREN = BASED + 40008;

    /**
     * 访问令牌已过期
     */
    public static final Integer ACCESS_TOKEN_HAS_EXPIRED = BASED + 40009;

    /**
     * 验证失败
     */
    public static final Integer AUTHENTICATION_FAILED = BASED + 40010;

    /**
     * 数据已存在
     */
    public static final Integer DATA_ALREADY_EXISTS = BASED + 40011;



    public ApiCode() {
        throw new AssertionError();
    }

}

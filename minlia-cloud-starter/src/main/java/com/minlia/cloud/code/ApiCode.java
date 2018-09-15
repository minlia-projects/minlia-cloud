//package com.minlia.cloud.code;
//
//import com.minlia.cloud.annotation.i18n.Localize;
//import com.minlia.cloud.annotation.i18n.Localized;
//import com.minlia.cloud.constant.Constants.LanguageTypes;
//
///**
// * Created by will on 6/19/17.
// * API 业务返回码定义
// */
//@Localized
//public abstract class ApiCode {
//
//    public ApiCode() {
//        throw new AssertionError();
//    }
//
//    /**
//     * 基数
//     */
//    public static final int BASED_ON = 10000;
//
//
//    /**
//     * 资源找不到
//     */
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Not Found: the API you requested could not be found."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "请求的API无法找到"),
//    })
//    public static final int NOT_FOUND = BASED_ON + 30004;
//
//
//    /**
//     * 未登录
//     */
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Not Login: Please login first."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "未登录, 请先登录."),
//    })
//    public static final int NOT_LOGIN = BASED_ON + 30001;
//
//    /**
//     * 无权限访问
//     */
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Not Authorized: Please confirm your permission."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "未授权, 请确认权限."),
//    })
//    public static final int NOT_AUTHORIZED = BASED_ON + 30003;
//
//
//    /**
//     * 不能为空
//     */
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Not Null: Please confirm that could not be null."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "不能为空, 请确保不为空."),
//    })
//    public static final int NOT_NULL = BASED_ON + 40001;
//
//    /**
//     * 无效验证码
//     */
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Invalid Secure Code: Please confirm that the validity."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "无效验证码, 请确保验证码的有效性."),
//    })
//    public static final int INVALID_SECURE_CODE = BASED_ON + 40002;
//
//
//    /**
//     * 无效用户名或密码
//     */
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Invalid Credential: Please confirm your credential."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "无效登录凭证, 请确保用户名与密码的有效性."),
//    })
//    public static final int INVALID_CREDENTIAL = BASED_ON + 40003;
//
//
//    /**
//     * 账户已禁用
//     */
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Account Unavailable: Please confirm your credential is not locked."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "账户不可用, 请确认账户是否被锁定."),
//    })
//    public static final int ACCOUNT_DISABLED = BASED_ON + 40004;
//
//    /**
//     * 登录失败
//     */
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Login Failed: Please confirm your credential."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "登录失败, 请确认账户与密码是否匹配."),
//    })
//    public static final int LOGIN_FAILED = BASED_ON + 40005;
//    /**
//     * 无效用户
//     */
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Invalid User: Please confirm your credential."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "无效用户, 请确认账户与密码是否匹配."),
//    })
//    public static final int INVALID_USER = BASED_ON + 40006;
//
//    /**
//     * 无效的原密码
//     */
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Invalid Raw Password: Please confirm your raw password."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "无效的原始密码, 请确认原始密码是否正确."),
//    })
//    public static final int INVALID_RAW_PASSWORD = BASED_ON + 40007;
//
//    /**
//     * 还有子项无法删除
//     */
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Has Children: Please confirm the sub items is empty."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "尚有子项,无法删除: 请确认子项已全部删除"),
//    })
//    public static final int COULD_NOT_DELETE_HAS_CHILDREN = BASED_ON + 40008;
//
//    /**
//     * 访问令牌已过期
//     */
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Access Token Expired: Please extend or login again."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "访问令牌已过期: 请延期或重新登录"),
//    })
//    public static final int ACCESS_TOKEN_HAS_EXPIRED = BASED_ON + 40009;
//
//    /**
//     * 验证失败
//     */
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Authentication Failed: Please confirm your credential."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "认证失败: 请确认登录凭证是否正确."),
//    })
//    public static final int AUTHENTICATION_FAILED = BASED_ON + 40010;
//
//    /**
//     * 数据已存在
//     */
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Data Already Exists: Please confirm the data you requested."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "数据已存在: 请确认请求的数据是否存在."),
//    })
//    public static final int DATA_ALREADY_EXISTS = BASED_ON + 40011;
//    /**
//     * 不支持的请求类型
//     */
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Unsupported Request Method: Please confirm the request method."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "不支持的请求方法: 请确认请求的方法."),
//    })
//    public static final int UNSUPPORTED_REQUEST_METHOD= BASED_ON + 40012;
//
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Access Token Invalid: Please confirm the token requested."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "访问令牌无效: 请确认请求的令牌"),
//    })
//    public static final int ACCESS_TOKEN_INVALID = BASED_ON + 40013;
//
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "User is not available: Please confirm that the requested username is already occupied."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "用户不可用: 请确认请求的用户名是否已被占用"),
//    })
//    public static final int USER_IS_NOT_AVAILABLE = BASED_ON + 40014;
//
//
//    @Localized(values={
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "en_US",message = "Remote business invoked with failure result."),
//        @Localize(type= LanguageTypes.ExceptionsApiCode,locale = "zh_CN",message = "远程业务调用返回结果不成功"),
//    })
//    public static final int REMOTE_BUSINESS_INVOKED_WITH_FAILURE_RESULT = BASED_ON + 40015;
//
//
//
//}

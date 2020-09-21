//package com.minlia.cloud.setting;
//
////import com.minlia.cloud.utils.PreconditionsHelper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.Assert;
//
//import java.io.InputStream;
//import java.util.Enumeration;
//
///**
// * 框架配置
// */
//@Slf4j
//public class FrameworkConfiguration {
//    public static final String CK_BASH_URL = "userfiles";
//    // 对/错
//    public final static String SYSTEM_TRUE = "true";
//    public final static String SYSTEM_FALSE = "false";
//    // 是/否
//    public static final Integer YES = 1;
//    public static final Integer NO = 0;
//    // 是/否
//    public static final String STR_YES = "1";
//    public static final String STR_NO = "0";
//    public static final String CONDITION_EQ = "eq";
//    public static final String CONDITION_SQL_EQ = "=";
//    public static final String CONDITION_NE = "ne";
//    public static final String CONDITION_SQL_NE = "!=";
//    public static final String CONDITION_BETWEEN = "between";
//    public static final String CONDITION_IN = "in";
//    public static final String CONDITION_NOTIN = "not in";
//    public static final String CONDITION_EXIST = "exist";
//    public static final String CONDITION_NOTEXIST = "notexist";
//    public static final String CONDITION_GE = "ge";
//    public static final String CONDITION_SQL_GE = ">=";
//    public static final String CONDITION_GT = "gt";
//    public static final String CONDITION_SQL_GT = ">";
//    public static final String CONDITION_LE = "le";
//    public static final String CONDITION_SQL_LE = "<=";
//    public static final String CONDITION_LT = "lt";
//    public static final String CONDITION_SQL_LT = "<";
//    public static final String CONDITION_LIKE = "like";
//    public static final String CONDITION_ILIKE = "ilike";
//    public static final String CONDITION_QUERY = "query";
//    public static final String CONDITION_EQPROPERTY = "eqproperty";
//    public static final String CONDITION_NEPROPERTY = "neproperty";
//    public static final String CONDITION_GEPROPERTY = "geproperty";
//    public static final String CONDITION_GTPROPERTY = "gtproperty";
//    public static final String CONDITION_LEPROPERTY = "leproperty";
//    public static final String CONDITION_LTPROPERTY = "ltproperty";
//    public static final String CONDITION_OR = "or";
//    public static final String CONDITION_AND = "and";
//    public static final String SORT_DESC = "desc";
//    public static final String SORT_ASC = "asc";
//    public static final String SPACE = " ";
//    private static java.util.Properties props = null;
//
//    static {
//        try {
//            reload(null);
//        } catch (Exception e) {
//            //nothing happens when no system.properties found
////            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 将构造函数私有化，不能new实例
//     */
//    private FrameworkConfiguration() {
//
//    }
//
//    /**
//     * 获取配置信息的静态方法。
//     *
//     * @param name - 要获取的配置信息的名称
//     * @return - 配置信息。如果不存在，返回null
//     */
//    public static String get(String name) {
//        if (props == null) {
//            return null;
//        }
//        try {
//            if (props.getProperty(name) != null) {
//                return props.getProperty(name).trim();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    /**
//     * @param name
//     * @return
//     */
//    public static Integer getInteger(String name, int defaultValue) {
//        String config = get(name);
//        return PreconditionsHelper.parseInt(config, defaultValue);
//    }
//
//    public static Integer getInteger(String name) {
//        String config = get(name);
//        return PreconditionsHelper.parseInt(config, null);
//    }
//
//    /**
//     * @param name
//     * @return
//     */
//    public static Long getLong(String name, long defaultValue) {
//        String config = get(name);
//        return PreconditionsHelper.parseLong(config, defaultValue);
//    }
//
//    public static Long getLong(String name) {
//        String config = get(name);
//        return PreconditionsHelper.parseLong(config, null);
//    }
//
//    public static Double getDouble(String name) {
//        String config = get(name);
//        return PreconditionsHelper.parseDouble(config);
//    }
//
//    /**
//     * 获取配置文件是否为 true 返回 true, 否则返回false
//     *
//     * @param name
//     * @return
//     */
//    public static boolean getBoolean(String name) {
//        String config = get(name);
//        if (PreconditionsHelper.isNotEmpty(config) && SYSTEM_TRUE.equalsIgnoreCase(config)) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 重新装载配置信息。
//     *
//     * @throws Exception
//     */
//    public static void reload(String path) throws Exception {
//        if (props == null) {
//            props = new java.util.Properties();
//        }
//        FrameworkConfiguration sc = new FrameworkConfiguration();
//        InputStream is = sc.getClass().getResourceAsStream(PreconditionsHelper.isEmpty(path) ? "/system.properties" : path);// properties
//        // in
//        // the
//        // classpath
//        props.load(is);
//        // dumpFrameworkConfiguration();
//    }
//
//    /**
//     * 输出配置信息到日志。
//     */
//    public static void dumpFrameworkConfiguration() {
//        String key, value;
//        if (props != null) {
//            Enumeration<Object> e = props.keys();
//            while (e.hasMoreElements()) {
//                key = (String) e.nextElement();
//                value = (String) props.get(key);
//                log.info("system.properties: " + key + " = " + value);
//            }
//        }
//    }
//
//    /**
//     * rsa 公钥
//     *
//     * @return
//     */
//    public static String getRsaPublicKey() {
//        return get("system.rsa.publicKey");
//    }
//
//    /**
//     * rsa私钥
//     *
//     * @return
//     */
//    public static String getRsaPrivateKey() {
//        return get("system.rsa.privateKey");
//    }
//
//    /**
//     * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
//     */
//    public static Boolean isDemoMode() {
//        return getBoolean("demoMode");
//    }
//
//    /**
//     * 是否是开发模式，演示模式下不用登录
//     */
//    public static Boolean isDevelopMode() {
//        return getBoolean("developMode");
//    }
//
//    /**
//     * 获取CKFinder上传文件的根目录
//     *
//     * @return
//     */
//    public static String getCkBaseDir() {
//        String dir = get("userfiles.basedir");
//        Assert.hasText(dir, "配置文件里没有配置userfiles.basedir属性");
//        if (!dir.endsWith("/")) {
//            dir += "/";
//        }
//        return dir;
//    }
//
//    public static boolean isMySql() {
//        return "mysql".equals(get("jdbc.type"));
//    }
//
//    public static boolean isOracle() {
//        return "oracle".equals(get("jdbc.type"));
//    }
//
//}

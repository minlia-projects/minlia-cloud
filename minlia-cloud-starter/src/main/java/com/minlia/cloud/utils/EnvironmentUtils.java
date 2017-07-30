package com.minlia.cloud.utils;

import com.minlia.cloud.holder.ContextHolder;
import com.minlia.cloud.setting.Globals;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.core.env.Environment;

/**
 * Created by will on 6/20/17.
 * 当前运行环境工具类
 */
public class EnvironmentUtils {


    /**
     * 是否为生产环境
     * @return
     */
    public static Boolean isProduction(){
        Environment environment = ContextHolder.getContext().getEnvironment();
        if(!ArrayUtils.contains(environment.getActiveProfiles(), Globals.Profiles.PRODUCTION)){
            return false;
        }else{
            return true;
        }
    }
}

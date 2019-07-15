package com.minlia.cloud.code;

/**
 * Created by garen on 2018/9/14.
 */
public interface Code {

    String code();

    String i18nKey();

    String message(Object... args);

}

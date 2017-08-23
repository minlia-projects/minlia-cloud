package com.minlia.cloud.annotation.i18n;

import com.minlia.cloud.constant.Constants.LanguageTypes;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by will on 8/22/17.
 */
@Target({  FIELD})
@Retention(RUNTIME)
@Inherited
public @interface Localize {

    String locale() default "en_US";

    String message() default "";

    /**
     * 国际化译文编码
     * @return
     */
    String code() default "";

    LanguageTypes type() default LanguageTypes.Message;
}
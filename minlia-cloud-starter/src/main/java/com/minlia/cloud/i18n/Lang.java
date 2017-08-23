package com.minlia.cloud.i18n;

import com.minlia.cloud.holder.ContextHolder;

import java.util.Locale;
import org.springframework.context.i18n.LocaleContextHolder;

public class Lang {

    public static String get(String key, Object[] arguments, Locale locale) {
        return ContextHolder.getContext().getMessage(key, arguments, locale);
    }

    public static String get(String key, Object[] arguments) {
        return ContextHolder.getContext().getMessage(key, arguments, LocaleContextHolder.getLocale());
    }

    public static String get(String key) {
        return ContextHolder.getContext().getMessage(key, new Object[]{}, LocaleContextHolder.getLocale());
    }
}

package com.minlia.cloud.i18n;

import com.minlia.cloud.holder.ContextHolder;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class Lang {

    public static String get(String key, Object[] args, Locale locale) {
        return ContextHolder.getContext().getMessage(key, args, locale);
    }

    public static String get(String key, Object[] args) {
        return ContextHolder.getContext().getMessage(key, args, LocaleContextHolder.getLocale());
    }

    public static String get(String key) {
        return ContextHolder.getContext().getMessage(key, new Object[]{}, LocaleContextHolder.getLocale());
    }
    
}

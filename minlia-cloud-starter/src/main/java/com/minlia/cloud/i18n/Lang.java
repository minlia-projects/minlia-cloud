package com.minlia.cloud.i18n;

import com.minlia.cloud.holder.ContextHolder;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class Lang {

    public static String get(String key, Object[] args, Locale locale) {
        try {
            return ContextHolder.getContext().getMessage(key, args, locale);
        } catch (NoSuchMessageException e) {
            return key;
        }
    }

    public static String get(String key, Object[] args) {
        return get(key, args, LocaleContextHolder.getLocale());
    }

    public static String get(String key) {
        return get(key, new Object[]{});
    }

}

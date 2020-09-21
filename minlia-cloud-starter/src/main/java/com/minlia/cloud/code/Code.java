package com.minlia.cloud.code;

import com.google.common.base.CaseFormat;
import com.minlia.cloud.constant.SymbolConstants;
import com.minlia.cloud.i18n.Lang;

import java.util.StringJoiner;

/**
 * @author garen
 * @date 2018/9/14
 */
public interface Code {

    /**
     * 模块名称
     *
     * @return
     */
    String module();

    /**
     * 枚举名称
     *
     * @return
     */
    default String code() {
        return ((Enum) this).name();
    }

    /**
     * 国际化key
     *
     * @return
     */
    default String i18nKey() {
        return i18nKey(module(), (Enum) this);
    }

    /**
     * 国际化值
     *
     * @param args
     * @return
     */
    default String message(Object... args) {
        return Lang.get(i18nKey(module(), ((Enum) this)), args);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    String message(Object ... var);
=======
    String message(Object... args);
>>>>>>> master
=======
    /**
     * 国际化key   内部无法获取模块名，所以间接获取 TODO
     *
     * @param module
     * @param e
     * @return
     */
    static String i18nKey(String module, Enum e) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE, new StringJoiner(SymbolConstants.DOT)
                .add(module)
                .add(e.getClass().getSimpleName())
                .add(e.name()).toString());
    }
>>>>>>> master

}
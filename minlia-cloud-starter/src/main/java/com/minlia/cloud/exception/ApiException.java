package com.minlia.cloud.exception;

import com.minlia.cloud.body.Response;
import com.minlia.cloud.code.Code;
import com.minlia.cloud.i18n.Lang;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by user on 2/24/17.
 */
@ResponseStatus(value = HttpStatus.OK)
public class ApiException extends NestedRuntimeException {

    private int status;

    private int code;

    private Object[] args;

    @Deprecated
    public ApiException(String msg) {
        super(msg);
        this.status = Response.FAILURE;
        this.code = Response.FAILURE;
        this.args = new Object[]{};
    }

    public ApiException(Code code, Object... args) {
        super(Lang.get(code.i18nKey(), args));
        this.status = Response.FAILURE;
        this.code = code.code();
        this.args = args;
    }

    public int getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

    public Object[] getArgs() {
        return args;
    }

}

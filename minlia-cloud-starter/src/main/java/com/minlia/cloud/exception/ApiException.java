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

    private HttpStatus status;

    private String code;

    private Object[] args;

    @Deprecated
    public ApiException(String msg) {
        super(msg);
        this.status = HttpStatus.OK;
        this.code = Response.CODE_FAILURE;
        this.args = new Object[]{};
    }

    public ApiException(HttpStatus status, String code, String msg) {
        super(msg);
        this.status = status;
        this.code = code;
        this.args = new Object[]{};
    }

    public ApiException(String code, String msg) {
        super(msg);
        this.status = HttpStatus.OK;
        this.code = code;
        this.args = new Object[]{};
    }

    public ApiException(HttpStatus status, Code code, Object... args) {
        super(Lang.get(code.i18nKey(), args));
        this.status = status;
        this.code = code.code();
        this.args = args;
    }

    public ApiException(Code code, Object... args) {
        super(Lang.get(code.i18nKey(), args));
        this.status = HttpStatus.OK;
        this.code = code.code();
        this.args = args;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Object[] getArgs() {
        return args;
    }

}

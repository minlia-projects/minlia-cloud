package com.minlia.cloud.exception;

import com.minlia.cloud.body.StatefulBody;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by user on 2/24/17.
 */
@ResponseStatus(value = HttpStatus.OK)
public class ApiException extends NestedRuntimeException {

    private int code;
    private int status;
    private Object[] arguments;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ApiException(String msg) {
        super(msg);
        this.status = StatefulBody.FAILURE;
        this.code = StatefulBody.FAILURE;
        this.arguments = new Object[]{};
    }

    public ApiException(int code) {
        super(String.format("%s%s%s", "Exceptions", getClassForStatic().getSimpleName(), code));
        this.code = code;
        this.status = StatefulBody.FAILURE;
        this.arguments = new Object[]{};
    }

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
        this.status = StatefulBody.FAILURE;
        this.arguments = new Object[]{};
    }

    public ApiException(int code, String msg, Object... arguments) {
        super(msg);
        this.code = code;
        this.status = StatefulBody.FAILURE;
        this.arguments = arguments;
    }

    private static final Class<?> getClassForStatic() {
        return new Object() {
            public Class<?> getClassForStatic() {
                return this.getClass();
            }
        }.getClassForStatic();
    }

    public ApiException() {
        super(String.format("%s%s", "Exceptions", getClassForStatic().getSimpleName()));
    }

    public ApiException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}

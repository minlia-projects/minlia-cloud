package com.minlia.cloud.exception.handler;

import com.minlia.cloud.code.SystemCode;
import com.minlia.cloud.exception.ApiException;
import com.minlia.cloud.exception.ApiExceptionResponseBody;
import com.minlia.cloud.exception.ValidationErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
//import org.springframework.web.util.NestedServletException;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    Environment environment;

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    protected final ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        log.error("HttpMessageNotReadableException: {}", ex.getMessage());
        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(status, ex);
//
//        if(ex.getCause() instanceof InvalidFormatException){
//            Class targetType= ((InvalidFormatException) ex.getCause()).getTargetType();
//            String message=ex.getMessage().replace(" of type "+targetType.getName()+"","");
//            message=message.replaceAll("Could not read document: ","");
//            message=message.replaceAll("declared Enum instance","declared");
//            message=message.substring(0,message.indexOf("\n at"));
//            responseBody.setMessage(message);
//        } else {
//
//        }
        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected final ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        log.error("MethodArgumentNotValidException: {}", ex.getMessage());
        final BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        final ValidationErrorDTO dto = processFieldErrors(fieldErrors);
        ApiExceptionResponseBody responseBody = new ApiExceptionResponseBody(status.name(), fieldErrors.get(0).getDefaultMessage(), ex);
        responseBody.setPayload(dto.getErrorDetails());
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        return handleExceptionInternal(ex, responseBody, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("NoHandlerFoundException: {}", ex.getMessage());
        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(status, ex);
        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

//    /**
//     * 权限异常
//     * @param ex
//     * @param request
//     * @return
//     */
//    @ExceptionHandler(AuthenticationException.class)
//    protected ResponseEntity<Object> handAuthentication(final AuthenticationException ex, final WebRequest request) {
//        ApiExceptionResponseBody apiExceptionResponseBody = new ApiExceptionResponseBody(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(),ex);
//        return handleExceptionInternal(ex, apiExceptionResponseBody, new HttpHeaders(), HttpStatus.OK, request);
//    }

    /**
     * 自定义异常
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({ApiException.class})
    protected ResponseEntity<Object> handleApiException(final ApiException ex, final WebRequest request) {
        log.warn("Api Exception: ", ex);
        ApiExceptionResponseBody responseBody = new ApiExceptionResponseBody(ex.getStatus(), ex.getCode(), ex.getMessage(), ex);
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.OK, request);
    }

    /**
     * 403 无权限
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<Object> handleAccessDeniedException(final AccessDeniedException ex, final WebRequest request) {
        log.warn("Access Denied Exception: ", ex);
        ApiExceptionResponseBody responseBody = new ApiExceptionResponseBody(HttpStatus.FORBIDDEN, SystemCode.Exception.FORBIDDEN, ex);
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.OK, request);
    }

    /**
     * spring dao 异常
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({DataAccessException.class})
    protected ResponseEntity<Object> handleDataAccessException(final DataAccessException ex, final WebRequest request) {
        log.error("DataAccessException: ", ex);
        final ApiExceptionResponseBody responseBody = new ApiExceptionResponseBody(SystemCode.Exception.INTERNAL_SERVER_ERROR.code(), ex);
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.OK, request);
    }

    /**
     * Insert或Update数据时违反了完整性，例如违反了惟一性限制
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleDataIntegrityViolationException(final DataIntegrityViolationException ex, final WebRequest request) {
        log.error("DataIntegrityViolationException: ", ex);
        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(SystemCode.Exception.INTERNAL_SERVER_ERROR.code(), "违反数据约束", ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.OK, request);
    }

    /**
     * 违反唯一约束
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    public final ResponseEntity<Object> handleDuplicateKey(final DuplicateKeyException ex, WebRequest request) {
        log.error("DuplicateKeyException: ", ex);
        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(SystemCode.Exception.INTERNAL_SERVER_ERROR.code(), "记录已存在，请勿重复操作", ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.OK, request);
    }

    @ExceptionHandler({Exception.class})
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected final ResponseEntity<Object> handleRuntime(final Exception ex, final WebRequest request) {
        log.error("Exception: ", ex);
        if (ex.getCause() instanceof ApiException) {
            ApiException apiException = (ApiException) ex.getCause();
            ApiExceptionResponseBody responseBody = new ApiExceptionResponseBody(apiException.getCode(), apiException.getMessage(), apiException);
            return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.OK, request);
        } else {
            final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(SystemCode.Exception.INTERNAL_SERVER_ERROR, ex);
            return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.OK, request);
        }
    }

    private ValidationErrorDTO processFieldErrors(final List<FieldError> fieldErrors) {
        final ValidationErrorDTO dto = new ValidationErrorDTO();
        for (final FieldError fieldError : fieldErrors) {
            dto.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return dto;
    }

}
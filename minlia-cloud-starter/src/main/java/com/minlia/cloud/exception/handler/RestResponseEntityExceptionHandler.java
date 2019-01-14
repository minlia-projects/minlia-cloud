/*
 * Copyright © 2016 Minlia (http://oss.minlia.com/license/framework/2016)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.minlia.cloud.exception.handler;

import com.minlia.cloud.code.SystemCode;
import com.minlia.cloud.exception.ApiException;
import com.minlia.cloud.exception.ApiExceptionResponseBody;
import com.minlia.cloud.exception.ValidationErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public RestResponseEntityExceptionHandler() {
        super();
    }

    @Override
    protected final ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        log.error("Bad Request: {}", ex.getMessage());
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
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected final ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        log.error("MethodArgumentNotValidException: {}", ex.getMessage());
        final BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        final ValidationErrorDTO dto = processFieldErrors(fieldErrors);
        ApiExceptionResponseBody responseBody = new ApiExceptionResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), fieldErrors.get(0).getDefaultMessage(), ex);
        responseBody.setPayload(dto.getErrorDetails());
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        return handleExceptionInternal(ex, responseBody, headers, HttpStatus.OK, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Bad Request: {}", ex.getMessage());
        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(HttpStatus.NOT_FOUND.value(), ex);
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
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({ApiException.class})
    protected ResponseEntity<Object> handleApi(final ApiException ex, final WebRequest request) {
        log.warn("Api Exception: ", ex);
        ApiExceptionResponseBody apiExceptionResponseBody = new ApiExceptionResponseBody(ex.getCode(), ex.getMessage(), ex);
        return handleExceptionInternal(ex, apiExceptionResponseBody, new HttpHeaders(), HttpStatus.OK, request);
    }

    // 403 无权限
    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<Object> handleAccessDenied(final AccessDeniedException ex, final WebRequest request) {
        log.warn("Access Denied Exception: ", ex);
//        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(HttpStatus.FORBIDDEN, e);
        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(SystemCode.Exception.FORBIDDEN, ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    /**
     * spring dao 异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler({DataAccessException.class})
    protected ResponseEntity<Object> handleDataAccess(final DataAccessException e, final WebRequest request) {
        log.error("DataAccessException: ", e);
        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), "DAO异常", e);
        return handleExceptionInternal(e, apiError, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    /**
     * Insert或Update数据时违反了完整性，例如违反了惟一性限制
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public final ResponseEntity<Object> dataIntegrityViolation(final DataIntegrityViolationException e, final WebRequest request) {
        log.error("DataIntegrityViolationException: ", e);
        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), "违反数据约束", e);
        return handleExceptionInternal(e, apiError, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    /**
     * 违反唯一约束
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    public final ResponseEntity<Object> handleDuplicateKey(final DuplicateKeyException e, final WebRequest request) {
        log.error("DuplicateKeyException: ", e);
        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), "记录已存在，请勿重复操作", e);
        return handleExceptionInternal(e, apiError, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    /**
     * 500异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler({Exception.class})
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected final ResponseEntity<Object> handleRuntime(final Exception e, final WebRequest request) {
        log.error("Internal Server Error: ", e);
        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        return handleExceptionInternal(e, apiError, new HttpHeaders(), HttpStatus.OK, request);
    }

    private ValidationErrorDTO processFieldErrors(final List<FieldError> fieldErrors) {
        final ValidationErrorDTO dto = new ValidationErrorDTO();
        for (final FieldError fieldError : fieldErrors) {
            dto.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return dto;
    }

}

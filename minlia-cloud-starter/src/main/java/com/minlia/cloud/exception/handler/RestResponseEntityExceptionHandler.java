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

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.minlia.cloud.exception.ApiException;
import com.minlia.cloud.exception.ApiExceptionResponseBody;
import com.minlia.cloud.exception.ValidationErrorDTO;
import com.minlia.cloud.i18n.Lang;
import com.minlia.cloud.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.InvalidMimeTypeException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
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
        log.info("Bad Request: {}", ex.getMessage());

        ApiExceptionResponseBody responseBody=new ApiExceptionResponseBody();

        if(ex.getCause() instanceof InvalidFormatException){
            Class targetType= ((InvalidFormatException) ex.getCause()).getTargetType();
            String message=ex.getMessage().replace(" of type "+targetType.getName()+"","");
            message=message.replaceAll("Could not read document: ","");
            message=message.replaceAll("declared Enum instance","declared");
            message=message.substring(0,message.indexOf("\n at"));
            responseBody.setMessage(message);
        }
        responseBody.setStatus(HttpStatus.OK.value());
        return handleExceptionInternal(ex, responseBody, headers, HttpStatus.OK, request);
    }

    @Override
    protected final ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        log.info("Bad Request: {}", ex.getMessage());
        //log.debug("Bad Request: ", ex);

        final BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        final ValidationErrorDTO dto = processFieldErrors(fieldErrors);

        ApiExceptionResponseBody responseBody=new ApiExceptionResponseBody();
        responseBody.setPayload(dto.getErrorDetails());
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        return handleExceptionInternal(ex, responseBody, headers, HttpStatus.OK, request);
    }

//    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
//    public final ResponseEntity<Object> handleMissingServletRequestParameterRequest(final RuntimeException ex, final WebRequest request) {
//        log.info("Bad Request: {}", ex.getLocalizedMessage());
//        final ApiExceptionResponseBody apiError = message(HttpStatus.BAD_REQUEST, ex);
//        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }

    /**
     * 运行时异常 500
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    protected final ResponseEntity<Object> handleRuntime(final HttpMessageNotReadableException e, final WebRequest request) {
        log.warn("Internal Server Error: {}", e.getMessage());
        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(HttpStatus.OK.value(), e);
        return handleExceptionInternal(e, apiError, new HttpHeaders(), HttpStatus.OK, request);
    }

    /**
     * api exception 200
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({ApiException.class})
    protected ResponseEntity<Object> handleApi(final ApiException ex, final WebRequest request) {
        log.warn("Api Exception: {}", ex.getMessage());

        String message = ex.getMessage();
        // 如果message为空
        // 1、根据code从国际化获取
        // 2、ex.getMessage()
        if (StringUtil.isBlank(message)) {
            String i18nKey = ex.getClass().getPackage().getName()+"."+StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(ex.getClass().getSimpleName()), ".").toLowerCase()+"."+ex.getCode();
            message = Lang.get(i18nKey);
            if (StringUtil.isBlank(message)) {
                message = ex.getMessage();
            }
        }

//        String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
//        //fallback message
//        if (StringUtils.isEmpty(message)) {
//            message = ex.getClass().getSimpleName();
//            message= ex.getClass().getPackage().getName()+"."+StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(message), ".").toLowerCase()+"."+ex.getCode();
//        }else {
//            message = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(message), ".").toLowerCase();
//        }
//        String devMessage ="";
//        if(!environment.acceptsProfiles(Globals.Profiles.PRODUCTION)) {
//            devMessage = String.format("%s with message: [%s]", ex.getClass().getSimpleName(), message);
//        }

        ApiExceptionResponseBody apiExceptionResponseBody = new ApiExceptionResponseBody(ex.getCode(), message,ex);
        return handleExceptionInternal(ex, apiExceptionResponseBody, new HttpHeaders(), HttpStatus.OK, request);
    }

    // 403 无权限
    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<Object> handleAccessDenied(final AccessDeniedException ex, final WebRequest request) {
        log.warn("Access Denied Exception: {}", ex.getMessage());
        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(HttpStatus.FORBIDDEN.value(), ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    // 404
    @ExceptionHandler({EntityNotFoundException.class, ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        log.warn("Not Found: {}", ex.getMessage());
        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(HttpStatus.NOT_FOUND.value(), ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // 415
    @ExceptionHandler({InvalidMimeTypeException.class, InvalidMediaTypeException.class})
    protected ResponseEntity<Object> handleInvalidMimeTypeException(final IllegalArgumentException ex, final WebRequest request) {
        log.warn("Unsupported Media Type: {}", ex.getMessage());
        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(HttpStatus.OK.value(), ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.OK, request);
    }

    /**
     * spring dao 异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler({DataAccessException.class})
    protected ResponseEntity<Object> handleConflict(final DataAccessException e, final WebRequest request) {
        log.warn("Conflict: {}", e.getMessage());
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
        log.warn("DataIntegrityViolation: {}", e.getMessage());
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
        log.warn("DuplicateKey: {}", e.getMessage());
        final ApiExceptionResponseBody apiError = new ApiExceptionResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), "记录已存在，请勿重复操作", e);
        return handleExceptionInternal(e, apiError, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    // UTIL
    private ValidationErrorDTO processFieldErrors(final List<FieldError> fieldErrors) {
        final ValidationErrorDTO dto = new ValidationErrorDTO();

        for (final FieldError fieldError : fieldErrors) {
            final String localizedErrorMessage = fieldError.getDefaultMessage();
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }

        return dto;
    }

}

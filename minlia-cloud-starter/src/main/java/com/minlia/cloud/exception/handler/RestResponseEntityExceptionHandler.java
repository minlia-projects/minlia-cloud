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
import com.minlia.cloud.setting.Globals;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.List;
//import org.springframework.web.util.NestedServletException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    Environment environment;
    public RestResponseEntityExceptionHandler() {
        super();
    }

    // API

    // 200 api exception

    @ExceptionHandler({ApiException.class})
    protected ResponseEntity<Object> handleServiceException(final ApiException ex, final WebRequest request) {
        log.warn("Api Exception: {}", ex.getMessage());

        ApiExceptionResponseBody apiExceptionResponseBody = null;

        String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
        String localizedMessage = null;

        //fallback message
        if (StringUtils.isEmpty(message)) {
            message = ex.getClass().getSimpleName();
            message= ex.getClass().getPackage().getName()+"."+StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(message), ".").toLowerCase()+"."+ex.getCode();
        }else {
            message = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(message), ".").toLowerCase();
        }
        String devMessage ="";

        if(!environment.acceptsProfiles(Globals.Profiles.PRODUCTION)) {
            if(ex.getTranslateRequired() !=null && ex.getTranslateRequired()) {
                devMessage = String.format("%s with message: [%s]", ex.getClass().getSimpleName(), message);
            }else{
                devMessage = String.format("%s with message: [%s]", ex.getClass().getSimpleName(), ex.getMessage());
            }
        }
        try {
            if(ex.getTranslateRequired() !=null && ex.getTranslateRequired()) {
                localizedMessage = Lang.get(message);
            }else{
                localizedMessage=ex.getMessage();
            }
        } catch (NoSuchMessageException e) {
//            localizedMessage="{{"+message+"}}";
            //maybe cause NoSuchMessageException just catch it here.
        }
        if (!StringUtils.isEmpty(localizedMessage)) {
            message = localizedMessage;
        }

        apiExceptionResponseBody = new ApiExceptionResponseBody(ex.getCode(), message, devMessage);

        return handleExceptionInternal(ex, apiExceptionResponseBody, new HttpHeaders(), HttpStatus.OK, request);
    }

    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }


    // 400

//    @Override
//    protected final ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
//        log.info("Bad Request: {}", ex.getMessage());
////        //log.debug("Bad Request: ", ex);
//
////        final ApiExceptionResponseBody apiError = message(HttpStatus.OK, ex);
////        apiError.setPayload(ex.getMessage());
//        ApiExceptionResponseBody responseBody=new ApiExceptionResponseBody();
////        responseBody.setMessage(ex.);
//
//        if(ex.getCause() instanceof InvalidFormatException){
//           Class targetType= ((InvalidFormatException) ex.getCause()).getTargetType();
////           Object value= ((InvalidFormatException) ex.getCause()).getValue();
////            Boolean isValid= EnumUtils.isValidEnum(targetType,value.toString());
//            String message=ex.getMessage().replace(" of type "+targetType.getName()+"","");
//            message=message.replaceAll("Could not read document: ","");
//            message=message.replaceAll("declared Enum instance","declared");
//            message=message.substring(0,message.indexOf("\n at"));
//            responseBody.setMessage(message);
////            if(!isValid) {
////                responseBody.setPayload(null);
////                List<String> types=new ArrayList(Arrays.asList(targetType.getEnumConstants()));//EnumUtils.getEnumList(targetType);
////                List<String> targetTypes= new ArrayList<>();
////                for(String type:types){
//////                    String formatted=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,type);
////                    String formatted=type.toLowerCase().replace("_",".");
//////                    formatted=formatted.replace("_",".");
////                    targetTypes.add(formatted);
////                }
////                responseBody.setMessage(value + " is not a valid value, must be one of ["+ targetTypes.stream().map(Object::toString)
////                        .collect(Collectors.joining(", "))+"]");
////            }
//        }
//        responseBody.setStatus(HttpStatus.OK.value());
//        return handleExceptionInternal(ex, responseBody, headers, HttpStatus.OK, request);
//    }
//
//
//    @Override
//    protected final ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
//        log.info("Bad Request: {}", ex.getMessage());
//        //log.debug("Bad Request: ", ex);
//
//        final BindingResult result = ex.getBindingResult();
//        final List<FieldError> fieldErrors = result.getFieldErrors();
//        final ValidationErrorDTO dto = processFieldErrors(fieldErrors);
//
//        ApiExceptionResponseBody responseBody=new ApiExceptionResponseBody();
//        responseBody.setPayload(dto.getErrorDetails());
//        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
//        return handleExceptionInternal(ex, responseBody, headers, HttpStatus.OK, request);
//    }
//
//    @ExceptionHandler(value = {ConstraintViolationException.class, DataIntegrityViolationException.class})
//    public final ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
//        log.info("Bad Request: {}", ex.getLocalizedMessage());
//        //log.debug("Bad Request: ", ex);
//
////        ApiExceptionResponseBody responseBody=new ApiExceptionResponseBody();
////        responseBody.setPayload(dto);
//
//        final ApiExceptionResponseBody apiError = message(HttpStatus.BAD_REQUEST, ex);
//        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//
//    // 403
//
////     @ExceptionHandler({ AccessDeniedException.class })
////     public ResponseEntity<Object> handleEverything(final AccessDeniedException ex, final WebRequest request) {
////     logger.error("403 Status Code", ex);
////
//////     final ApiError apiError = message(HttpStatus.FORBIDDEN, ex);
////     final ApiExceptionResponseBody apiError = message(HttpStatus.BAD_REQUEST, ex);
////     return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
////     }
//
//    // 404
//
//    @ExceptionHandler({EntityNotFoundException.class, ResourceNotFoundException.class})
//    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
//        log.warn("Not Found: {}", ex.getMessage());
//
//        final ApiExceptionResponseBody apiError = message(HttpStatus.NOT_FOUND, ex);
//        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
//    }
//
//    @ExceptionHandler({AccessDeniedException.class})
//    protected ResponseEntity<Object> handleAccessDenied(final RuntimeException ex, final WebRequest request) {
//        log.warn("Access Denied Exception: {}", ex.getMessage());
//
//        final ApiExceptionResponseBody apiError = message(HttpStatus.FORBIDDEN, ex);
//        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
//    }
//
//    // 409
//
//    @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class, EmptyResultDataAccessException.class})
//    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
//        log.warn("Conflict: {}", ex.getMessage());
//
//        final ApiExceptionResponseBody apiError = message(HttpStatus.CONFLICT, ex);
//        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.CONFLICT, request);
//    }
//
//    // 415
//
//    @ExceptionHandler({InvalidMimeTypeException.class, InvalidMediaTypeException.class})
//    protected ResponseEntity<Object> handleInvalidMimeTypeException(final IllegalArgumentException ex, final WebRequest request) {
//        log.warn("Unsupported Media Type: {}", ex.getMessage());
//
//        final ApiExceptionResponseBody apiError = message(HttpStatus.OK, ex);
//        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.OK, request);
//    }
//
//    // 500
//
//    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
//    public ResponseEntity<Object> handle500s(final RuntimeException ex, final WebRequest request) {
//        logger.error("500 Status Code", ex);
//
//        final ApiExceptionResponseBody apiError = message(HttpStatus.OK, ex);
//
//        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.OK, request);
//    }
//
//    // UTIL
//
//    private ValidationErrorDTO processFieldErrors(final List<FieldError> fieldErrors) {
//        final ValidationErrorDTO dto = new ValidationErrorDTO();
//
//        for (final FieldError fieldError : fieldErrors) {
//            final String localizedErrorMessage = fieldError.getDefaultMessage();
//            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
//        }
//
//        return dto;
//    }
//
//    private ApiExceptionResponseBody message(final HttpStatus httpStatus, final Exception ex) {
//        final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
//        final String devMessage = ex.getClass().getSimpleName();
//        // devMessage = ExceptionUtils.getStackTrace(ex);
//
//        return new ApiExceptionResponseBody(httpStatus.value(), message, devMessage);
//    }

}

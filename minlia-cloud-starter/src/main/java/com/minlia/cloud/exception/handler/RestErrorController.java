package com.minlia.cloud.exception.handler;

import com.minlia.cloud.body.Response;
import com.minlia.cloud.constant.Constants.LanguageTypes;
import com.minlia.cloud.utils.Environments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Based on the helpful answer at http://stackoverflow.com/q/25356781/56285,
 * with error details in response body added.
 * 添加404 JSON输出
 */
@RestController
public class RestErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    /**
     * //强制触发抛出异常, 主要还是通过 RestResponseEntityExceptionHandler.class,这里只是捡漏
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
    Response error(HttpServletRequest request, HttpServletResponse response) {
//        {
//            "timestamp": 1536935424270,
//                "status": 404,
//                "error": "Not Found",
//                "message": "Not Found",
//                "path": "/api/user/registration"
//        }

        response.setStatus(200);
        Map<String, Object> errorAttributes = getErrorAttributes(request, !Environments.isProduction());
        Integer status = (Integer) errorAttributes.get("status");
        String path = (String) errorAttributes.get("path");
        String messageFound = (String) errorAttributes.get("message");
        Integer messageStatus = extractStatus(messageFound, status);
        if (HttpStatus.NOT_FOUND.value() != status) {

        }
        return Response.failure(status,messageFound);
    }

    private Integer extractStatus(String message, Integer status) {
        if (message.startsWith(LanguageTypes.ExceptionsApiCode.name())) {
            int index = LanguageTypes.ExceptionsApiCode.name().length();
            String message2 = message.substring(index);
            return Integer.valueOf(message2);
        } else {
            return status;
        }
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

}
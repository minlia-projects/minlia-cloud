package com.minlia.cloud.exception.handler;

import com.minlia.cloud.body.StatefulBody;
import com.minlia.cloud.body.impl.FailureResponseBody;
import com.minlia.cloud.utils.EnvironmentUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
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
public class RestErrorEndpoint implements ErrorController {

    private static final String PATH = "/error";


    @Autowired
    private ErrorAttributes errorAttributes;

//    @RequestMapping(value = PATH)
    @RequestMapping(value = PATH,  produces = {MediaType.APPLICATION_JSON_VALUE})
    StatefulBody error(HttpServletRequest request, HttpServletResponse response) {
        // Appropriate HTTP response code (e.g. 404 or 500) is automatically set by Spring. 
        // Here we just define response body.
//        return new ErrorJson(response.getStatus(), getErrorAttributes(request, debug));

        if(!EnvironmentUtils.isProduction()) {
            return buildBody(request,true);
        }else{
            return buildBody(request,false);
        }
    }


    private StatefulBody buildBody(HttpServletRequest request,Boolean includeStackTrace){
        Map<String,Object> errorAttributes = getErrorAttributes(request, includeStackTrace);
        Integer status=(Integer)errorAttributes.get("status");
        String path=(String)errorAttributes.get("path");
        String messageFound=(String)errorAttributes.get("message");
        String message="";
        String trace ="";
        if(!StringUtils.isEmpty(path)){
            message=String.format("Requested path %s with result %s",path,messageFound);
        }
        if(includeStackTrace) {
             trace = (String) errorAttributes.get("trace");
             if(!StringUtils.isEmpty(trace)) {
                 message += String.format(" and trace %s", trace);
             }
        }
        return FailureResponseBody.builder().code(0).status(status).message(message).build();
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
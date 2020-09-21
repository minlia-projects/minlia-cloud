//package com.minlia.cloud.exception.handler;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.error.ErrorAttributes;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import springfox.documentation.service.ResponseMessage;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
//@RestController
//public class ExceptionController implements ErrorController {
//
//    @Autowired
//    private ErrorAttributes errorAttributes;
//
//    /**
//     * 默认错误
//     */
//    private static final String PATH_DEFAULT = "/error";
//
//    @Override
//    public String getErrorPath() {
//        return PATH_DEFAULT;
//    }
//
//    /**
//     * JSON格式错误信息
//     */
//    @RequestMapping(value = PATH_DEFAULT, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseMessage error(HttpServletRequest request) {
//        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
//        Map<String, Object> body = this.errorAttributes.getErrorAttributes(requestAttributes, true);
//        return ResponseMessage.fail("服务器端异常！", body);
//    }
//
//}
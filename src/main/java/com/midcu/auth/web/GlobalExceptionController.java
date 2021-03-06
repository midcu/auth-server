package com.midcu.auth.web;

import com.midcu.auth.utils.JsonRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionController {

    @ExceptionHandler(Throwable.class)
    public JsonRes handleThrowable(Throwable e){

        log.error("请求发生错误：", e);
        return JsonRes.Bad("请求发生错误！");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public JsonRes handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){

        return JsonRes.Bad("请求参数不正确！");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonRes handleHttpRequestMethodNotSupportedException(){

        return JsonRes.Bad("请求方法不支持！");
    }

    @ExceptionHandler(BindException.class)
    public JsonRes handleBindException(BindException e){

        StringBuilder msg = new StringBuilder();
        for(ObjectError error : e.getBindingResult().getAllErrors()) {
            String message = error.getDefaultMessage();
            if (StringUtils.hasText(message) && message.startsWith("Failed to convert value")) {
                msg.append("请求参数类型有误！");
            } else {
                msg.append(message);
            }
        }

        return JsonRes.Bad(msg.toString());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public JsonRes handleAccessDeniedException(AccessDeniedException e){

        return JsonRes.Status("没有操作权限！", HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public JsonRes handleIllegalArgumentException(IllegalArgumentException e){

        return JsonRes.Err(e.getMessage());
    }

}

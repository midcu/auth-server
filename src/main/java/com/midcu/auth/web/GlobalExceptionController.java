package com.midcu.auth.web;

import com.midcu.auth.utils.JsonRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionController {

    @Value("${spring.profiles.active:prod}") String profilesActive;

    @ExceptionHandler(Throwable.class)
    public JsonRes handleException(Throwable e){

        if (profilesActive.equals("dev") || profilesActive.equals("test")) {
            log.error("请求发生错误：", e);
            return JsonRes.Bad(e.getMessage());
        } else {
            return JsonRes.Bad("请求发生错误！");
        }
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonRes handleException(){

        return JsonRes.Bad("请求方法不支持！");
    }

    @ExceptionHandler(BindException.class)
    public JsonRes handleException(BindException e){

        List<ObjectError> errorList = e.getBindingResult().getAllErrors();
        StringBuilder msg = new StringBuilder();
        if (errorList != null) {
            for(ObjectError error : errorList) {
                msg.append(error.getDefaultMessage());
            }
        }

        return JsonRes.Bad(msg.toString());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public JsonRes handleAccessDeniedException(AccessDeniedException e){

        return JsonRes.Status("没有操作权限！", HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public JsonRes handleAccessDeniedException(IllegalArgumentException e){

        return JsonRes.Err(e.getMessage());
    }

}

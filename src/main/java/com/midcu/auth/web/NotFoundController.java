package com.midcu.auth.web;

import com.midcu.auth.utils.JsonRes;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotFoundController implements ErrorController{

	@RequestMapping("/error")
    @Operation(hidden = true)
    public JsonRes error() {
		return JsonRes.Status("404！您所访问的内容不存在！", HttpStatus.NOT_FOUND.value());
    }
}

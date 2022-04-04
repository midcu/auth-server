package com.midcu.auth.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonResponse {
    
    public static void setJsonResult(HttpServletResponse response, String message, int status) throws IOException {

        response.setStatus(status);

        response.setContentType("application/json; charset=UTF-8");

        response.getWriter().print(JsonRes.Status(message, status).toMessageString());
    }
}

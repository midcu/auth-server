package com.midcu.auth.component;

import com.midcu.auth.utils.JsonRes;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CaptchaFilter extends OncePerRequestFilter{

    @Resource
    private AuthModuleConfig authModuleConfig;
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, AccessDeniedException {

        if (!StringUtils.endsWithIgnoreCase(authModuleConfig.getLoginUrl(), request.getRequestURI()) || !StringUtils.endsWithIgnoreCase("post", request.getMethod())) {
            // 非Login POST方法
            filterChain.doFilter(request, response);
        } else {
            String codeName = request.getParameter(authModuleConfig.getCaptchaCodeName());
    
             if (!StringUtils.hasText(codeName)) {
                response.setStatus(200);
                response.setContentType("application/json; charset=UTF-8");
    
                response.getWriter().print(JsonRes.Status("未填写验证码！", HttpServletResponse.SC_BAD_REQUEST).toMessageString());
                
            } else if (!CaptchaUtil.ver(codeName, request)) {
                CaptchaUtil.clear(request);
    
                response.setStatus(200);
                response.setContentType("application/json; charset=UTF-8");
    
                response.getWriter().print(JsonRes.Status("验证码错误！", HttpServletResponse.SC_BAD_REQUEST).toMessageString());
            } else {
                CaptchaUtil.clear(request);
                filterChain.doFilter(request, response);
            }
        }


    }
 
}

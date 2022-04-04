package com.midcu.auth.component;

import com.midcu.auth.utils.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Configuration
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private AuthModuleConfig authModuleConfig;

	@Resource
	private CaptchaFilter captchaFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().disable().csrf().disable();

		if (authModuleConfig.getCaptcha()) {
			// 是否开启验证码拦截
			http.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);
		}

		http.formLogin()
				.successHandler((request, response, authentication) -> JsonResponse.setJsonResult(response, "登录成功！", HttpServletResponse.SC_OK))
				.failureHandler((request, response, exception) -> JsonResponse.setJsonResult(response, exception.getMessage(), HttpServletResponse.SC_BAD_REQUEST)).permitAll();

		http.logout().logoutSuccessHandler((request, response, authentication) -> JsonResponse.setJsonResult(response, "退出成功！", HttpServletResponse.SC_OK)).permitAll();


		if (authModuleConfig.getPermitUrl() != null) {
			log.info("无需验证的路径：" + authModuleConfig.getPermitUrl());
			http.authorizeRequests().antMatchers(authModuleConfig.getPermitUrl().split(",")).permitAll();
		}

		http.authorizeRequests().anyRequest().authenticated();

		http.exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
			// 访问未授权资源时，返回401
			JsonResponse.setJsonResult(response, "没有操作权限！", HttpServletResponse.SC_UNAUTHORIZED);
		})
		.authenticationEntryPoint((request, response, authException) -> {
			// 未登录或者登录超时，返回403
			JsonResponse.setJsonResult(response, "禁止访问！", HttpServletResponse.SC_FORBIDDEN);
		});
		
	}

	@Bean
    public FilterRegistrationBean<CaptchaFilter> registration(CaptchaFilter filter) {
        FilterRegistrationBean<CaptchaFilter> registration = new FilterRegistrationBean<CaptchaFilter>(filter);
        registration.setEnabled(false);
        return registration;
    }

}

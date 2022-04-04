package com.midcu.auth.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("midcu.auth.security")
@Data
public class AuthModuleConfig {

    private String permitUrl;

    private Boolean captcha = true;

    // 是否开启管理员账号
    private Boolean admin = false;

    // 管理员账号名称
    private String adminUsername = "admin";

    private String adminPassword;

    private String loginUrl = "/login";

    private String captchaCodeName = "captchaCode";
}

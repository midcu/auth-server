package com.midcu.auth.web.ro;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserRo{

    @NotBlank(message = "请填写登录用户名！")
    private String username;
    
    private String nickname;

    @NotBlank(message = "请填写登陆密码！")
    private String password;

    private String description;

    private String phone;

    private String email;

    @NotNull(message = "请设置状态！")
    private Integer state;
    
}

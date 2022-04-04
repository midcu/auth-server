package com.midcu.auth.web.ro;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PermissionRo {

    @NotBlank(message = "请填写权限名称！")
    private String name;

    @NotBlank(message = "请填写权限标题！")
    private String title;

    private String description;

    @NotNull(message = "请填写父ID！")
    private Long pid;
    
    @NotNull(message = "请设置状态！")
    private Integer state;
}

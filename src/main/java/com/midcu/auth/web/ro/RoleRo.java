package com.midcu.auth.web.ro;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RoleRo{
    @NotBlank(message = "请填写角色名称！")
    private String name;

    private String description;

    @NotNull(message = "请设置状态！")
    private Integer state;
    
}

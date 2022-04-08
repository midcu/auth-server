package com.midcu.auth.web.ro;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MenuRo {

    @NotNull(message = "请选择父节点！")
    private Long pid;

    @NotBlank(message = "请填写路由路径！")
    private String path;

    private Boolean display;

    @NotBlank(message = "请填写标题！")
    private String title;

    private String name;

    private String description;

    private String component;

    private String icon;

    private String layout;

    @NotNull(message = "请选择类型！")
    private Integer type;

    @NotNull(message = "请设置排序位置！")
    private Integer sort;

    private Boolean iframe;

    private String iframeSrc;

    @NotNull(message = "请设置状态！")
    private Integer state;

    @NotNull(message = "请设置所属系统！")
    private Long platformId;

}

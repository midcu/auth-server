package com.midcu.auth.web.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuVo implements Serializable {
    private final Long id;
    private final Long pid;
    private final String path;
    private final Boolean display;
    private final String title;
    private final String component;
    private final String name;
    private final String description;
    private final String icon;
    private final String layout;
    private final Integer type;
    private final Integer sort;
    private final Boolean iframe;
    private final String iframeSrc;
    private final Integer belongTo;
}

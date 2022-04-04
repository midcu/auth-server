package com.midcu.auth.dao.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuDto implements Serializable {
    private final Long id;
    private final Long pid;
    private final String title;
    private final String description;
    private final String icon;
}

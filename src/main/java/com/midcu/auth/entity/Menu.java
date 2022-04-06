package com.midcu.auth.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name="sys_menu")
@DynamicInsert
@DynamicUpdate
public class Menu extends BaseAuditable {

    @Column(nullable = false)
    private Long pid;

    @Column(nullable = false)
    private String path;

    private Boolean display;

    @Column(nullable = false)
    private String title;

    private String component;

    private String name;

    private String description;

    private String icon;

    private String layout;

    @Column(nullable = false)
    private Integer type;

    @Column(nullable = false)
    private Integer sort;

    private Boolean iframe;

    private String iframeSrc;

    /**
     * 所属系统 多对一关系
     */
    private Integer belongTo;

}

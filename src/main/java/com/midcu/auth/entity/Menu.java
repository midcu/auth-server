package com.midcu.auth.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
     * 所属系统 多对 一 关系
     */
    @Column(nullable = false)
    private Long platformId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Menu menu = (Menu) o;
        return getId() != null && Objects.equals(getId(), menu.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

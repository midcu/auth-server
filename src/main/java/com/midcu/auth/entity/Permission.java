package com.midcu.auth.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name="sys_permission")
@DynamicInsert
@DynamicUpdate
public class Permission extends BaseAuditable{

    @Column(unique = true)
    public String name;

    public String title;

    public String description;

    @Column(nullable = false)
    public Long pid;

}

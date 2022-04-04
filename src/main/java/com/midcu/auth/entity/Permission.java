package com.midcu.auth.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name="sys_permission")
public class Permission extends BaseAuditable{

    @Column(unique = true)
    public String name;

    public String title;

    public String description;

    @Column(nullable = false)
    public Long pid;

}

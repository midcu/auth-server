package com.midcu.auth.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name="sys_role")
public class Role extends BaseAuditable{

    private String name;

    @Column(unique = true)
    private String permit;

    private String description;

}

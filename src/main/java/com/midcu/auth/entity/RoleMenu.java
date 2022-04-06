package com.midcu.auth.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name="sys_role_menu")
@DynamicInsert
@DynamicUpdate
public class RoleMenu extends BaseAuditable {

    public Long roleId;

    public Long menuId;
}

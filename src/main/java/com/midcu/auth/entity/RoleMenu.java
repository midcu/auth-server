package com.midcu.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name="sys_role_menu")
public class RoleMenu extends BaseAuditable {

    public Long roleId;

    public Long menuId;
}

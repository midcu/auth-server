package com.midcu.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name="sys_user_role")
public class UserRole extends BaseAuditable{

    public Long userId;

    public Long roleId;

}

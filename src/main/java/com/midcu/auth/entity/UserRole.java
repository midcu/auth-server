package com.midcu.auth.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name="sys_user_role")
@DynamicInsert
@DynamicUpdate
public class UserRole extends BaseAuditable{

    public Long userId;

    public Long roleId;

}

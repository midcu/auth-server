package com.midcu.auth.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_platform")
@DynamicUpdate
@DynamicInsert
@Data
public class Platform extends BaseAuditable {

    private String name;

}

package com.midcu.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name="sys_user")
public class User extends BaseAuditable{

    @Column(unique = true)
    public String username;
    
    public String nickname;

    @JsonIgnore
    public String password;

    public String description;

    public String phone;

    public String email;

}
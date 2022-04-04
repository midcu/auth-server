package com.midcu.auth.dao.dto;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class PermissionDto implements GrantedAuthority{

    public String name;

    @Override
    public String getAuthority() {

        return this.name;
    }
    
}

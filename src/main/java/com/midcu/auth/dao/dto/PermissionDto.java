package com.midcu.auth.dao.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@JsonRootName(value = "permission")
public class PermissionDto implements GrantedAuthority{

    public String name;

    @JsonCreator
    public PermissionDto(@JsonProperty("name") String name) {
        this.name = name;
    }

    @Override
    @JsonGetter("name")
    public String getAuthority() {
        return this.name;
    }

}

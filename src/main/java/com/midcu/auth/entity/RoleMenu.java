package com.midcu.auth.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="sys_role_menu")
@DynamicInsert
@DynamicUpdate
public class RoleMenu extends BaseAuditable {

    public Long roleId;

    public Long menuId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RoleMenu roleMenu = (RoleMenu) o;
        return getId() != null && Objects.equals(getId(), roleMenu.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

package com.midcu.auth.service;

import com.midcu.auth.entity.Menu;
import com.midcu.auth.entity.Permission;
import com.midcu.auth.entity.Role;
import com.midcu.auth.web.qo.RoleQuery;
import com.midcu.auth.web.ro.RoleRo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {

    Page<Role> findAll(Pageable pageable, RoleQuery roleQuery);

    Role save(RoleRo roleRo);

    Role update(RoleRo roleRo, Long id);

    void delete(Long id);

    Page<Menu> findRoleMenu(Long roleId);

    Page<Permission> findRolePermission(Long roleId);

    void setRolePermission(Long roleId, List<Long> permissionIds);

    void setRoleMenu(Long roleId, List<Long> menuIds);

}

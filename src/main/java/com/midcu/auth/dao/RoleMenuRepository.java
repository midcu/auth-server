package com.midcu.auth.dao;

import com.midcu.auth.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long> {

    void deleteByRoleId(Long roleId);

    void deleteByMenuId(Long menuId);

    Collection<RoleMenu> findAllByRoleId(Long roleId);

    Collection<RoleMenu> findAllByMenuId(Long menuId);
}
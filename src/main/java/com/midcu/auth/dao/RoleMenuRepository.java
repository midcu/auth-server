package com.midcu.auth.dao;

import com.midcu.auth.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long> {

    void deleteByRoleId(Long roleId);

    void deleteByMenuId(Long menuId);

    Collection<RoleMenu> findAllByRoleId(Long roleId);

    Collection<RoleMenu> findAllByMenuId(Long menuId);
}

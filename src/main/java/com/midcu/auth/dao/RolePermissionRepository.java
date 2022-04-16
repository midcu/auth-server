package com.midcu.auth.dao;

import com.midcu.auth.entity.RolePermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    void deleteByPermissionId(Long id);

    void deleteByRoleId(Long id);

    Page<RolePermission> findAllByRoleId(Long roleId, Pageable pageable);

    Collection<RolePermission> findAllByRoleId(Long roleId);

    Page<RolePermission> findAllByPermissionId(Long permissionId, Pageable pageable);

}

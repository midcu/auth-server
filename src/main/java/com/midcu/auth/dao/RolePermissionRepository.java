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

    //     @Query(value = "select new com.midcu.auth.dao.dto.PermissionDto(rp.id, permission) from RolePermission rp INNER JOIN Permission permission ON permission.id = rp.permissionId where rp.roleId = ?1 ")
//     Page<PermissionDto> findAllByRoleId(Long roleId, Pageable pageable);

    // @Query(value = "select new com.midcu.auth.dao.dto.PermissionDto(p.id, p.name) from RolePermission rp, Permission p where rp.roleId = ?1 and p.id = rp.permissionId ")
    // List<PermissionDto> findAllByRoleId(Long roleId);

//    @Query(value = "select rp from RolePermission rp where rp.roleId = ?1")
//    Page<RolePermission> findAllByRoleId(Long roleId, Pageable pageable);
}

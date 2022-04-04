package com.midcu.auth.dao;

import com.midcu.auth.dao.dto.PermissionDto;
import com.midcu.auth.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Page<Permission> findAllByIdIn(List<Long> ids, Pageable pageable);

    Page<PermissionDto> findAllBy(Pageable pageable);

    @Query(value = "select new com.midcu.auth.dao.dto.PermissionDto(p.name) from Permission p " +
            "inner join RolePermission rp on p.id = rp.permissionId inner join UserRole ur on rp.roleId = ur.roleId where ur.userId = ?1")
    List<PermissionDto> findAllByUserId(Long userId);
}
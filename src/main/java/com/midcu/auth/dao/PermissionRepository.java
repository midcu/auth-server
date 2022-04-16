package com.midcu.auth.dao;

import com.midcu.auth.dao.dto.PermissionDto;
import com.midcu.auth.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Page<Permission> findAllByIdIn(List<Long> ids, Pageable pageable);

    @Query(value = "select new com.midcu.auth.dao.dto.PermissionDto(p.name) from Permission p where p.state = ?1")
    List<PermissionDto> findAllByState(Integer state);

    @Query(value = "select new com.midcu.auth.dao.dto.PermissionDto(p.name) from Permission p " +
            "inner join RolePermission rp on p.id = rp.permissionId inner join UserRole ur on rp.roleId = ur.roleId where ur.userId = ?1 and p.state = 1")
    List<PermissionDto> findAllByUserId(Long userId);
}

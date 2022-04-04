package com.midcu.auth.service;

import com.midcu.auth.dao.dto.PermissionDto;
import com.midcu.auth.entity.Permission;
import com.midcu.auth.web.ro.PermissionRo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionService {

    Page<PermissionDto> findLiteAll(Pageable pageable);

    Page<Permission> findAll(Pageable pageable);

    Permission save(PermissionRo permissionRo);

    Permission update(PermissionRo permissionRo, Long id);

    void delete(Long id);
}

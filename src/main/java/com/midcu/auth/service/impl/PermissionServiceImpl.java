package com.midcu.auth.service.impl;

import com.midcu.auth.dao.PermissionRepository;
import com.midcu.auth.dao.RolePermissionRepository;
import com.midcu.auth.dao.dto.PermissionDto;
import com.midcu.auth.entity.Permission;
import com.midcu.auth.service.PermissionService;
import com.midcu.auth.utils.BeanCopyUtils;
import com.midcu.auth.web.ro.PermissionRo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    PermissionRepository permissionRepository;

    @Resource
    RolePermissionRepository rolePermissionRepository;

    @Override
    public Page<PermissionDto> findLiteAll(Pageable pageable) {
        return permissionRepository.findAllBy(pageable);
    }

    @Override
    public Page<Permission> findAll(Pageable pageable) {
        return permissionRepository.findAll(pageable);
    }

    @Override
    public Permission save(PermissionRo permissionRo) {

        Permission permission = new Permission();

        BeanCopyUtils.copyProperties(permissionRo, permission);

        return permissionRepository.save(permission);
    }

    @Override
    public Permission update(PermissionRo permissionRo, Long id) {

        Permission permission = permissionRepository.findById(id).get();

        Assert.notNull(permission, "更新的权限不存在！");

        BeanCopyUtils.copyProperties(permissionRo, permission);

        return permissionRepository.save(permission);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        permissionRepository.deleteById(id);
        rolePermissionRepository.deleteByPermissionId(id);
    }

}

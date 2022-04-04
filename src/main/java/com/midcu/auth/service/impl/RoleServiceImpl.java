package com.midcu.auth.service.impl;

import com.midcu.auth.dao.*;
import com.midcu.auth.entity.*;
import com.midcu.auth.service.RoleService;
import com.midcu.auth.utils.BeanCopyUtils;
import com.midcu.auth.web.qo.RoleQuery;
import com.midcu.auth.web.ro.RoleRo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleRepository roleRepository;

    @Resource
    MenuRepository menuRepository;

    @Resource
    PermissionRepository permissionRepository;

    @Resource
    RoleMenuRepository roleMenuRepository;

    @Resource
    RolePermissionRepository rolePermissionRepository;

    @Override
    public Page<Role> findAll(Pageable pageable, RoleQuery roleQuery) {

        Role role = new Role();

        BeanUtils.copyProperties(roleQuery, role);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return roleRepository.findAll(Example.of(role, exampleMatcher), pageable);

    }

    @Override
    public Role save(RoleRo roleRo) {

        Role role = new Role();

        BeanCopyUtils.copyProperties(roleRo, role);

        return roleRepository.save(role);
    }

    @Override
    public Role update(RoleRo roleRo, Long id) {

        Role role = roleRepository.findById(id).get();

        Assert.notNull(role, "更新的角色不存在！");

        BeanCopyUtils.copyProperties(roleRo, role);

        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // 删除角色
        roleRepository.deleteById(id);
        // 删除角色的菜单关系
        roleMenuRepository.deleteByRoleId(id);
        // 删除角色的权限关系
        rolePermissionRepository.deleteByRoleId(id);
    }

    @Override
    public Page<Menu> findRoleMenu(Long roleId) {

        List<Long> menuIds = roleMenuRepository.findAllByRoleId(roleId).stream().map(RoleMenu::getMenuId).collect(Collectors.toList());

        return menuRepository.findAllByIdInOrderBySort(menuIds, Pageable.unpaged());
    }

    @Override
    public Page<Permission> findRolePermission(Long roleId) {

        List<Long> permissionIds = rolePermissionRepository.findAllByRoleId(roleId).stream().map(RolePermission::getPermissionId).collect(Collectors.toList());

        return permissionRepository.findAllByIdIn(permissionIds, Pageable.unpaged());
    }

    @Override
    @Transactional
    public void setRolePermission(Long roleId, List<Long> permissionIds) {

        rolePermissionRepository.deleteByRoleId(roleId);

        List<RolePermission> rolePermissions = permissionIds.stream().map(id -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(id);
            rolePermission.setRoleId(roleId);
            rolePermission.setState(1);

            return rolePermission;
        }).collect(Collectors.toList());

        rolePermissionRepository.saveAll(rolePermissions);
    }

    @Override
    @Transactional
    public void setRoleMenu(Long roleId, List<Long> menuIds) {

        roleMenuRepository.deleteByRoleId(roleId);

        List<RoleMenu> rolePermissions = menuIds.stream().map(id -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(id);
            roleMenu.setRoleId(roleId);
            roleMenu.setState(1);

            return roleMenu;
        }).collect(Collectors.toList());

        roleMenuRepository.saveAll(rolePermissions);

    }
}

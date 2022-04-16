package com.midcu.auth.service.impl;

import com.midcu.auth.dao.*;
import com.midcu.auth.dao.dto.PermissionDto;
import com.midcu.auth.entity.Role;
import com.midcu.auth.entity.User;
import com.midcu.auth.entity.UserRole;
import com.midcu.auth.service.UserService;
import com.midcu.auth.utils.BeanCopyUtils;
import com.midcu.auth.web.qo.UserQuery;
import com.midcu.auth.web.ro.UserRo;
import com.midcu.auth.web.vo.MenuVo;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Resource
    UserRoleRepository userRoleRepository;

    @Resource
    RoleRepository roleRepository;

    @Resource
    MenuRepository menuRepository;

    @Resource
    PermissionRepository permissionRepository;

    @Override
    public Page<User> findAll(Pageable pageable, UserQuery userQuery) {

        User user = new User();

        BeanUtils.copyProperties(userQuery, user);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return userRepository.findAll(Example.of(user, exampleMatcher), pageable);
    }

    @Override
    public User save(UserRo userRo) {

        Assert.isTrue(!userRepository.existsByUsername(userRo.getUsername()), "用户名已存在！");

        User user = new User();

        BeanCopyUtils.copyProperties(userRo, user);

        user = userRepository.save(user);

        user.setPassword("");

        return user;
    }

    @Override
    public User update(UserRo userRo, Long userId) {

        Optional<User> optUser = userRepository.findById(userId);

        Assert.isTrue(optUser.isPresent(), "更新的用户不存在！");

        User user= optUser.get();

        BeanCopyUtils.copyProperties(userRo, user);

        user = userRepository.save(user);

        user.setPassword("");

        return user;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRoleRepository.deleteAllByUserId(id);
        userRepository.deleteById(id);
    }

    @Override
    public Page<Role> findUserRole(Long userId) {

        List<Long> roleIds = userRoleRepository.findAllByUserId(userId, Pageable.unpaged()).stream().map(UserRole::getRoleId).collect(Collectors.toList());

        return roleRepository.findAllByIdIn(roleIds, Pageable.unpaged());
    }

    @Override
    @Transactional
    public void setUserRole(List<Long> roleIds, Long userId) {
        userRoleRepository.deleteAllByUserId(userId);

        List<UserRole> userRoles = roleIds.stream().map(id -> {
            UserRole userRole = new UserRole();
            userRole.setRoleId(id);
            userRole.setUserId(userId);
            userRole.setState(1);
            return userRole;
        }).collect(Collectors.toList());

        userRoleRepository.saveAll(userRoles);

    }

    @Override
    public User findUserByUsername(String username) {

        Optional<User> optionalUser = userRepository.findUserByUsername(username);

        Assert.isTrue(optionalUser.isPresent(), "用户不存在！");

        return optionalUser.get();
    }

    @Override
    public User findUserDtoByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        Assert.isTrue(optionalUser.isPresent(), "用户不存在！");

        return optionalUser.get();
    }

    @Override
    public List<PermissionDto> findUserPermission(Long userId) {

        return permissionRepository.findAllByUserId(userId);
    }

    @Override
    public List<MenuVo> findUserMenu(Long userId, Long platformId) {
        return menuRepository.findLiteMenuByUserId(userId, platformId);
    }
}

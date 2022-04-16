package com.midcu.auth.service.impl;

import com.midcu.auth.component.AuthModuleConfig;
import com.midcu.auth.entity.User;
import com.midcu.auth.service.PermissionService;
import com.midcu.auth.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

    @Resource
    private UserService userServiceImpl;

    @Resource
    private PermissionService permissionServiceImpl;

    @Resource
    private AuthModuleConfig authModuleConfig;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (authModuleConfig.getAdmin() && username.equals(authModuleConfig.getAdminUsername())) {
            // 判断是否开启管理员账号功能 且是否为管理员账号
            UserDetailsImpl userDetails = new UserDetailsImpl();

            userDetails.setId(1L);
            userDetails.setUsername(username);
            userDetails.setStatus(1);

            Assert.notNull(authModuleConfig.getAdminPassword(), "未设置管理员密码");

            userDetails.setPassword(authModuleConfig.getAdminPassword());

            // 拥有所有的权限
            userDetails.setAuthorities(permissionServiceImpl.findLiteAll(1));

            return userDetails;
        } else {
            User user = userServiceImpl.findUserByUsername(username);

            UserDetailsImpl userDetails = new UserDetailsImpl();

            if (user == null) {
                throw new UsernameNotFoundException("用户名或密码错误！");
            }

            userDetails.setId(user.getId());
            userDetails.setUsername(user.getUsername());
            userDetails.setStatus(user.getState());
            userDetails.setPassword(user.getPassword());

            userDetails.setAuthorities(userServiceImpl.findUserPermission(user.getId()));

            return userDetails;
        }

    }

}

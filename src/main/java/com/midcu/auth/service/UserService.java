package com.midcu.auth.service;

import com.midcu.auth.dao.dto.PermissionDto;
import com.midcu.auth.entity.Role;
import com.midcu.auth.entity.User;
import com.midcu.auth.web.qo.UserQuery;
import com.midcu.auth.web.ro.UserRo;
import com.midcu.auth.web.vo.MenuVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<User> findAll(Pageable pageable, UserQuery userQuery);

    User save(UserRo userRo);

    User update(UserRo userRo, Long userId);

    void delete(Long id);

    Page<Role> findUserRole(Long userId);

    void setUserRole(List<Long> roleIds, Long userId);

    User findUserByUsername(String username);

    User findUserDtoByUsername(String username);

    List<PermissionDto> findUserPermission(Long userId);

    List<MenuVo> findUserMenu(Long userId);
}

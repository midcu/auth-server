package com.midcu.auth.web;

import com.midcu.auth.entity.Role;
import com.midcu.auth.service.UserService;
import com.midcu.auth.utils.JsonRes;
import com.midcu.auth.web.qo.UserQuery;
import com.midcu.auth.web.ro.UserRo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Tag(name = "系统：用户管理")
public class UsersController {

    @Resource
	private UserService userServiceImpl;

	@Operation(
		summary = "用户列表获取",
    	description = "获取所有的用户"
	)
	@GetMapping("/list")
	@PreAuthorize("hasAuthority('users:list')")
	public JsonRes search(@PageableDefault(sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable, UserQuery userQuery) {
		return JsonRes.OK(JsonRes.FIND, userServiceImpl.findAll(pageable.previousOrFirst(), userQuery));
	}

	@Operation(
		summary = "新增用户",
    	description = "新增用户"
	)
	@PostMapping
	@PreAuthorize("hasAuthority('users:add')")
	public JsonRes create(@Validated @RequestBody UserRo userRo) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		userRo.setPassword("{bcrypt}".concat(encoder.encode(userRo.getPassword())));

		return JsonRes.OK(JsonRes.SAVE, userServiceImpl.save(userRo));
	}

	@Operation(
		summary = "更新用户",
    	description = "更新用户的指定项::主要作用是更新用户密码，建议其他信息由用户自己更新！"
	)
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('users:edit')")
	public JsonRes update(@RequestBody UserRo userRo, @PathVariable("id") Long id) {

        if (userRo.getPassword() != null) {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            userRo.setPassword("{bcrypt}".concat(encoder.encode(userRo.getPassword())));
        }


		return JsonRes.OK(JsonRes.UPDATE, userServiceImpl.update(userRo, id));
	}

	@Operation(
		summary = "删除用户",
    	description = "根据用户ID删除用户"
	)
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('users:del')")
	public JsonRes del(@PathVariable("id") Long id) {

		userServiceImpl.delete(id);
		return JsonRes.OK(JsonRes.DELETE);
	}

	@Operation(
		summary = "获取用户角色",
    	description = "根据用户ID获取用户角色"
	)
	@GetMapping("/roles/{id}")
	@PreAuthorize("hasAuthority('users:roles:list')")
	public JsonRes userRoles(@PathVariable("id") Long id) {

		return JsonRes.OK(JsonRes.FIND, userServiceImpl.findUserRole(id).getContent().stream().map(Role::getId).collect(Collectors.toList()));
	}

	@Operation(
		summary = "设置用户角色",
    	description = "根据用户ID设置用户角色"
	)
	@PostMapping("/roles/{id}")
	@PreAuthorize("hasAuthority('users:roles:reset')")
	public JsonRes setRoles(@PathVariable("id") Long id, @RequestBody List<Long> roleIds) {

		userServiceImpl.setUserRole(roleIds, id);
		return JsonRes.OK("角色设置成功！");
	}

}

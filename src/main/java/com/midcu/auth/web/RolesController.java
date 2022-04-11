package com.midcu.auth.web;

import com.midcu.auth.service.PermissionService;
import com.midcu.auth.service.RoleService;
import com.midcu.auth.utils.JsonRes;
import com.midcu.auth.web.qo.RoleQuery;
import com.midcu.auth.web.ro.RoleRo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
@Tag(name = "系统：角色管理")
public class RolesController {
    
    @Resource
	private RoleService roleServiceImpl;

	@Resource
	private PermissionService permissionServiceImpl;

    @Operation(
		summary = "角色列表获取",
    	description = "获取所有的角色"
	)
	@GetMapping("/list")
	@PreAuthorize("hasAuthority('roles:list')")
	public JsonRes search(@PageableDefault(size = 10, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable, RoleQuery roleQuery) {
		return JsonRes.OK(JsonRes.FIND, roleServiceImpl.findAll(pageable.previousOrFirst(), roleQuery));
	}

	@Operation(
		summary = "新增角色",
		description = "新增角色"
	)
	@PostMapping
	@PreAuthorize("hasAuthority('roles:add')")
	public JsonRes create(@Validated @RequestBody RoleRo roleRo) {

		return JsonRes.OK(JsonRes.SAVE, roleServiceImpl.save(roleRo));
	}

	@Operation(
		summary = "更新角色",
    	description = "更新角色的指定项！"
	)
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('roles:edit')")
	public JsonRes update(@RequestBody RoleRo roleRo, @PathVariable("id") Long id) {
		return JsonRes.OK(JsonRes.UPDATE, roleServiceImpl.update(roleRo, id));
	}

	@Operation(
		summary = "根据角色获取菜单",
    	description = "根据角色获取菜单"
	)
	@GetMapping("/menus/{roleId}")
	@PreAuthorize("hasAuthority('roles:menus')")
	public JsonRes roleId(@PathVariable("roleId") Long roleId) {
		return JsonRes.OK(JsonRes.FIND, roleServiceImpl.findRoleMenu(roleId).getContent().stream().map(p -> p.getId()).collect(Collectors.toList()));
	}

	@Operation(
		summary = "删除角色",
    	description = "删除角色 同时删除角色对应的菜单和权限"
	)
	@DeleteMapping("/{roleId}")
	@PreAuthorize("hasAuthority('roles:del')")
	public JsonRes del(@PathVariable("roleId") Long roleId) {

		roleServiceImpl.delete(roleId);

		return JsonRes.OK(JsonRes.DELETE);
	}

	@Operation(
		summary = "根据角色获取权限",
    	description = "根据角色获取权限"
	)
	@GetMapping("/permissions/{roleId}")
	@PreAuthorize("hasAuthority('roles:permissions')")
	public JsonRes permission(@PathVariable("roleId") Long roleId) {
		return JsonRes.OK(JsonRes.FIND, roleServiceImpl.findRolePermission(roleId).getContent().stream().map(p -> p.getId()).collect(Collectors.toList()));
	}

	@Operation(
		summary = "设置角色权限",
    	description = "设置角色权限"
	)
	@PostMapping("/permissions/{roleId}")
	@PreAuthorize("hasAuthority('roles:permissions:reset')")
	public JsonRes setPermission(@PathVariable("roleId") Long roleId, @RequestBody List<Long> roleIds) {

		roleServiceImpl.setRolePermission(roleId, roleIds);

		return JsonRes.OK("权限设置成功！");
	}

	@Operation(
		summary = "设置角色菜单",
    	description = "设置角色菜单"
	)
	@PostMapping("/menus/{roleId}")
	@PreAuthorize("hasAuthority('roles:menus:reset')")
	public JsonRes setMenus(@PathVariable("roleId") Long roleId, @RequestBody List<Long> menuIds) {

		roleServiceImpl.setRoleMenu(roleId, menuIds);

		return JsonRes.OK("菜单设置成功！");
	}

}

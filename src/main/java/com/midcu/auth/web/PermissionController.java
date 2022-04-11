package com.midcu.auth.web;

import com.midcu.auth.service.PermissionService;
import com.midcu.auth.utils.JsonRes;
import com.midcu.auth.web.ro.PermissionRo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/permissions")
@Tag(name = "系统：权限管理")
public class PermissionController {
    
    @Resource
	private PermissionService permissionServiceImpl;

    @Operation(
		summary = "权限列表获取",
    	description = "获取所有的权限"
	)
	@GetMapping("/list")
	public JsonRes search() {
		return JsonRes.OK(JsonRes.FIND,permissionServiceImpl.findAll(Pageable.unpaged()));
	}

	@Operation(
		summary = "新增权限",
    	description = "新增权限"
	)
	@PostMapping
	public JsonRes create(@Validated @RequestBody PermissionRo permissionRo) {

		return JsonRes.OK(JsonRes.SAVE, permissionServiceImpl.save(permissionRo));
	}

	@Operation(
		summary = "更新权限",
    	description = "更新权限的指定描述和标识"
	)
	@PutMapping("/{id}")
	public JsonRes update(@RequestBody PermissionRo permissionRo, @PathVariable("id") Long id) {
		return JsonRes.OK(JsonRes.UPDATE, permissionServiceImpl.update(permissionRo, id));
	}
    
	@Operation(
		summary = "删除权限",
    	description = "根据权限ID删除权限"
	)
	@DeleteMapping("/{id}")
	public JsonRes del(@PathVariable("id") Long id) {
		permissionServiceImpl.delete(id);
		return JsonRes.OK(JsonRes.DELETE);
	}
}

package com.midcu.auth.web;

import com.midcu.auth.service.MenuService;
import com.midcu.auth.utils.JsonRes;
import com.midcu.auth.web.ro.MenuRo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/menus")
@Tag(name = "系统：菜单管理")
public class MenuController {

	@Resource
	private MenuService menuServiceImpl;

	@Operation(
		summary = "菜单列表获取",
    	description = "获取所有的菜单"
	)
	@GetMapping("/list")
	@PreAuthorize("hasAuthority('menus:list')")
	public JsonRes search() {

		return JsonRes.OK("查询成功！", menuServiceImpl.findAll());
	}

	@Operation(
		summary = "菜单列表获取",
    	description = "获取所有菜单的ID和名称"
	)
	@GetMapping("/lite")
	@PreAuthorize("hasAuthority('menus:list')")
	public JsonRes searchAll() {
		return JsonRes.OK("查询成功！", menuServiceImpl.findLiteMenu());
	}

	@Operation(
		summary = "新增菜单",
    	description = "添加新的菜单"
	)
	@PostMapping
	@PreAuthorize("hasAuthority('menus:add')")
	public JsonRes create(@Validated @RequestBody MenuRo menuRo) {

		return JsonRes.OK("保存成功！", menuServiceImpl.save(menuRo));
	}

	@Operation(
		summary = "更新菜单",
    	description = "更新菜单的指定项"
	)
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('menus:edit')")
	public JsonRes update(@RequestBody MenuRo menuRo, @PathVariable("id") Long id) {
		menuServiceImpl.update(menuRo, id);
		return JsonRes.OK("更新成功！");
	}

	@Operation(
		summary = "删除菜单",
    	description = "根据菜单ID删除菜单"
	)
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('menus:del')")
	public JsonRes del(@PathVariable("id") Long id) {

		menuServiceImpl.delete(id);

		return JsonRes.OK("删除成功！");
	}

}

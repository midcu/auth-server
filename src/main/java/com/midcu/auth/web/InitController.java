package com.midcu.auth.web;

import com.midcu.auth.component.AuthModuleConfig;
import com.midcu.auth.entity.User;
import com.midcu.auth.service.MenuService;
import com.midcu.auth.service.UserService;
import com.midcu.auth.service.impl.UserDetailsImpl;
import com.midcu.auth.web.vo.InfoVo;
import com.midcu.auth.web.vo.MenuVo;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@ResponseBody
@Tag(name = "用户：登录注册注销，前端信息初始化！")
public class InitController {
    
    @Resource
    private UserService userServiceImpl;

	@Resource
	private MenuService menuServiceImpl;

	@Resource
	private AuthModuleConfig authModuleConfig;

	@Operation(
		summary = "用户数据初始化",
    	description = "返回用户的角色，权限，菜单等内容，用于构建前端页面。"
	)
	@GetMapping("/init")
	
	public ResponseEntity<Object> init(Authentication  authentication) {

		
		UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();

		User user;
		List<MenuVo> menus;
		List<String> permissions;
		if (authModuleConfig.getAdmin() && userDetails.getUsername().equals(authModuleConfig.getAdminUsername())) {
			// 判断是否开启管理员账号功能 且是否为管理员账号
			user = new User();

			user.setId(1L);
			user.setPassword(authModuleConfig.getAdminPassword());
			user.setUsername(authModuleConfig.getAdminUsername());
			user.setState(1);
			user.setNickname("系统管理员");
			user.setDescription("系统管理员账号");

			// 拥有所有的菜单
			menus = menuServiceImpl.findLiteAllByState(1);

			permissions = userDetails.getAuthorities().stream().map(p -> p.getAuthority()).collect(Collectors.toList());

		} else {

			user = userServiceImpl.findUserDtoByUsername(userDetails.getUsername());

			menus = userServiceImpl.findUserMenu(user.getId());

			permissions = userDetails.getAuthorities().stream().map(p -> p.getAuthority()).collect(Collectors.toList());

		}
		return new ResponseEntity<>(new InfoVo(menus, user, permissions), HttpStatus.OK);
	}

	@Operation(
		summary = "验证码功能",
    	description = "获取验证码"
	)
	@GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CaptchaUtil.out(130, 32, 5, request, response);
    }
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String pwd = "{bcrypt}";

		pwd = "{bcrypt}".concat(encoder.encode("eea45e1507c2140a7abaed6c475e8b3e"));

		System.out.println(pwd);
	}
}

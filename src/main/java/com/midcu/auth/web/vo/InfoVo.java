package com.midcu.auth.web.vo;

import com.midcu.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InfoVo {

    private List<MenuVo> menus;

    private User user;

    private List<String> permissions;
}

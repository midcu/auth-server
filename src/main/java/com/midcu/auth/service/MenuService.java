package com.midcu.auth.service;

import com.midcu.auth.dao.dto.MenuDto;
import com.midcu.auth.entity.Menu;
import com.midcu.auth.web.ro.MenuRo;
import com.midcu.auth.web.vo.MenuVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MenuService {
    Page<Menu> findAll();

    Page<Menu> findAll(Long platformId);

    List<MenuVo> findLiteAllByState(Integer state, Long platformId);

    Page<MenuDto> findLiteMenu(Integer state);

    Menu save(MenuRo menuRo);

    Menu update(MenuRo menuRo, Long id);

    void delete(Long id);
}

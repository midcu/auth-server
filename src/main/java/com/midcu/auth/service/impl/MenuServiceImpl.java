package com.midcu.auth.service.impl;

import com.midcu.auth.dao.MenuRepository;
import com.midcu.auth.dao.RoleMenuRepository;
import com.midcu.auth.dao.dto.MenuDto;
import com.midcu.auth.entity.Menu;
import com.midcu.auth.service.MenuService;
import com.midcu.auth.utils.BeanCopyUtils;
import com.midcu.auth.web.ro.MenuRo;
import com.midcu.auth.web.vo.MenuVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuRepository menuRepository;

    @Resource
    RoleMenuRepository roleMenuRepository;

    @Override
    public Page<Menu> findAll() {
        return menuRepository.findAllByOrderBySort(Pageable.unpaged());
    }

    @Override
    public Page<Menu> findAllByState(Integer state) {
        return menuRepository.findAllByStateOrderBySort(state, Pageable.unpaged());
    }

    @Override
    public List<MenuVo> findLiteAllByState(Integer state) {
        return menuRepository.findAllByStateOrderBySort(state);
    }

    @Override
    public Page<MenuDto> findLiteMenu() {
        return menuRepository.findByOrderBySort(Pageable.unpaged());
    }

    @Override
    public Menu save(MenuRo menuRo) {

        Menu menu = new Menu();

        BeanCopyUtils.copyProperties(menuRo, menu);

        return menuRepository.save(menu);
    }

    @Override
    public Menu update(MenuRo menuRo, Long id) {

        Menu menu = menuRepository.findById(id).get();

        Assert.notNull(menu, "更新的菜单不存在！");

        BeanCopyUtils.copyProperties(menuRo, menu);

        return menuRepository.save(menu);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        menuRepository.deleteById(id);
        roleMenuRepository.deleteByMenuId(id);
    }
}

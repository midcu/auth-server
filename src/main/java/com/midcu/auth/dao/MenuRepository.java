package com.midcu.auth.dao;

import com.midcu.auth.dao.dto.MenuDto;
import com.midcu.auth.entity.Menu;
import com.midcu.auth.web.vo.MenuVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Page<Menu> findAllByOrderBySort(Pageable pageable);

    Page<Menu> findAllByStateOrderBySort(Integer state, Pageable pageable);

    List<MenuVo> findAllByStateOrderBySort(Integer state);

    Page<Menu> findAllByIdInOrderBySort(List<Long> ids, Pageable pageable);

    Page<MenuDto> findByOrderBySort(Pageable pageable);

    @Query(value = "select m from Menu m inner join RoleMenu rm on m.id = rm.menuId inner join UserRole ur on rm.roleId = ur.roleId where ur.userId = ?1 order by m.sort")
    Page<MenuVo> findByUserId(Long userId, Pageable pageable);

    @Query(value = "select new com.midcu.auth.web.vo.MenuVo(m.id, m.pid, m.path, m.display, m.title, m.component, m.name, m.description, m.icon, m.layout, m.type, m.sort, m.iframe, m.iframeSrc, m.belongTo) from Menu m inner join RoleMenu rm on m.id = rm.menuId inner join UserRole ur on rm.roleId = ur.roleId where ur.userId = ?1 order by m.sort")
    List<MenuVo> findLiteMenuByUserId(Long userId);

}
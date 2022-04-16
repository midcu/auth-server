package com.midcu.auth.dao;

import com.midcu.auth.dao.dto.MenuDto;
import com.midcu.auth.entity.Menu;
import com.midcu.auth.web.vo.MenuVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Page<Menu> findAllByOrderBySort(Pageable pageable);

    Page<Menu> findAllByPlatformIdOrderBySort(Long platformId, Pageable pageable);

    Page<Menu> findAllByStateOrderBySort(Integer state, Pageable pageable);

    List<MenuVo> findAllByStateAndPlatformIdOrderBySort(Integer state, Long platformId);

    Page<Menu> findAllByIdInOrderBySort(List<Long> ids, Pageable pageable);

    Page<MenuDto> findByStateOrderBySort(Integer state, Pageable pageable);

    @Query(value = "select m from Menu m inner join RoleMenu rm on m.id = rm.menuId inner join UserRole ur on rm.roleId = ur.roleId where ur.userId = ?1 order by m.sort")
    Page<MenuVo> findByUserId(Long userId, Pageable pageable);

    @Query(value = "select new com.midcu.auth.web.vo.MenuVo(m.id, m.pid, m.path, m.display, m.title, m.component, m.name, m.description, m.icon, m.layout, m.type, m.sort, m.iframe, m.iframeSrc, m.platformId) from Menu m inner join RoleMenu rm on m.id = rm.menuId inner join UserRole ur on rm.roleId = ur.roleId where ur.userId = ?1 and m.platformId = ?2 order by m.sort")
    List<MenuVo> findLiteMenuByUserId(Long userId, Long platformId);

}

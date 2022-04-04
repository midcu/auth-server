package com.midcu.auth.dao;

import com.midcu.auth.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Page<Role> findAllByIdIn(List<Long> roleIds, Pageable pageable);

}
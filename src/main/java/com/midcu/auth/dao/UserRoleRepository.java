package com.midcu.auth.dao;

import com.midcu.auth.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Page<UserRole> findAllByUserId(Long userId, Pageable pageable);

    void deleteAllByUserId(Long userId);

    void deleteAllByRoleId(Long roleId);
}

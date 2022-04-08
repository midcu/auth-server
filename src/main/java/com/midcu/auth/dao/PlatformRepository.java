package com.midcu.auth.dao;

import com.midcu.auth.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
}

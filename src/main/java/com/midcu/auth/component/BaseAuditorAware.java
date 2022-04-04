package com.midcu.auth.component;

import com.midcu.auth.service.impl.UserDetailsImpl;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BaseAuditorAware implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        } else {
            UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();

            return Optional.ofNullable(userDetails.getId());
        }
    }
}

package com.portfolio.ohousev1.config;

import com.portfolio.ohousev1.dto.post.PostPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing //audting서비스 설정
@Configuration
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(principal -> {
                    if (principal instanceof PostPrincipal) {
                        return ((PostPrincipal) principal).getUsername();
                    } else {
                        // 원하는 대체값 또는 오류 처리를 수행할 수 있습니다.
                        return null; // 또는 다른 값
                    }
                });

//                .map(PostPrincipal.class::cast)
//                .map(PostPrincipal::getUsername);
    }
}

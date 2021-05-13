package top.fosin.anan.platformapi.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 
 *
 * @author fosin
 * @date 2019.1.15
 */
@Configuration
@AllArgsConstructor
public class UserIdAuditorImpl implements AuditorAware<Long> {
    private final AnanUserDetailService ananUserDetailService;

    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(ananUserDetailService.getAnanUserId());
    }
}

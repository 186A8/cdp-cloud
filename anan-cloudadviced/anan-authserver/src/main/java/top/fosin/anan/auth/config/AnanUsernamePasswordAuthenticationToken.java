package top.fosin.anan.auth.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 
 * @author fosin
 */
public class AnanUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public AnanUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}

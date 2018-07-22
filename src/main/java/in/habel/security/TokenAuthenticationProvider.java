package in.habel.security;

import in.habel.models.StoreAuth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Optional;

public class TokenAuthenticationProvider implements AuthenticationProvider {

    private TokenService tokenService;

    public TokenAuthenticationProvider(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String apiId = (String) authentication.getPrincipal();
        String apiKey = (String) authentication.getCredentials();
        if (StringUtils.isBlank(apiId)) {
            throw new BadCredentialsException("Invalid store id");
        }
        if (StringUtils.isBlank(apiKey)) {
            throw new BadCredentialsException("Invalid api key");
        }
        Optional<StoreAuth> optionalStoreAuth = tokenService.contains(apiId, apiKey);
        if (!optionalStoreAuth.isPresent()) {
            authentication.setAuthenticated(false);
        }

        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }
}

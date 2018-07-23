package in.habel.security.user;

import in.habel.exceptions.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class AuthenticationFilter extends GenericFilterBean {

    public static final String AUTH_STORE_ID = "X-Auth-StoreId";
    public static final String AUTH_STORE_KEY = "X-Auth-StoreKey";
    private final static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = asHttp(request);
        HttpServletResponse httpResponse = asHttp(response);

        Optional<String> apiId = Optional.ofNullable(httpRequest.getHeader(AUTH_STORE_ID));
        Optional<String> apiKey = Optional.ofNullable(httpRequest.getHeader(AUTH_STORE_KEY));

        String resourcePath = new UrlPathHelper().getPathWithinApplication(httpRequest);

        try {


            if (apiKey.isPresent() && apiId.isPresent()) {
                logger.debug("Trying to authenticate user by {} : {} and {} : {} header", AUTH_STORE_ID, apiId, AUTH_STORE_KEY, apiKey);
                processTokenAuthentication(apiId, apiKey);
            }

            logger.debug("AuthenticationFilter is passing request down the filter chain");
            chain.doFilter(request, response);
        } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
            SecurityContextHolder.clearContext();
            logger.error("Internal authentication service exception", internalAuthenticationServiceException);
            httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (UnauthenticatedException authenticationException) {
            SecurityContextHolder.clearContext();
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
        } finally {
            MDC.remove(AUTH_STORE_ID);
            MDC.remove(AUTH_STORE_KEY);
        }
    }


    private HttpServletRequest asHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    private HttpServletResponse asHttp(ServletResponse response) {
        return (HttpServletResponse) response;
    }


    private void processTokenAuthentication(Optional<String> apiId, Optional<String> apiKey) {
        Authentication resultOfAuthentication = tryToAuthenticateWithToken(apiId, apiKey);
        SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
    }

    private Authentication tryToAuthenticateWithToken(Optional<String> token, Optional<String> apiKey) {
        PreAuthenticatedAuthenticationToken requestAuthentication = new PreAuthenticatedAuthenticationToken(token.get(), apiKey.get(), Collections.singletonList(() -> "ROLE_USER"));
        requestAuthentication.setAuthenticated(true);
        return tryToAuthenticate(requestAuthentication);
    }

    private Authentication tryToAuthenticate(Authentication requestAuthentication) throws AuthenticationException {
        Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
            throw new UnauthenticatedException("Unable to authenticate Domain User for provided credentials");
        }
        logger.debug("User successfully authenticated");
        return responseAuthentication;
    }
}

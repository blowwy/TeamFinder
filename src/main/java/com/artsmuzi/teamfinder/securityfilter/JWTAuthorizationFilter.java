package com.artsmuzi.teamfinder.securityfilter;

import com.artsmuzi.teamfinder.constants.SecurityConstants;
import com.artsmuzi.teamfinder.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final TokenService tokenService;
    private final Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, TokenService tokenService) {
        super(authenticationManager);
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstants.HEADER);
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        Authentication auth = getTokenAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(auth);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getTokenAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER);
        if(token != null) {
            String tmp = token.replace(SecurityConstants.TOKEN_PREFIX,"");
            String username = tokenService.getUsernameFromJWT(tmp);
            if(username != null)
                return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        }

        return null;
    }
}

package com.spring.euler.configuration.security;

import com.spring.euler.common.exception.ApiError;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class RequestFilter extends OncePerRequestFilter {
    private static final List<String> AUTH_WHITELIST_EXACT = List.of(
            "/authenticate",
            "/v2/api-docs",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html");

    private static final List<String> AUTH_WHITELIST_WILDCARD = List.of("/webjars", "/swagger-resources");

    @Autowired
    private JWTUserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return AUTH_WHITELIST_EXACT.contains(request.getRequestURI()) ||
                AUTH_WHITELIST_WILDCARD.stream().anyMatch(x -> request.getRequestURI().startsWith(x));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String token = request.getHeader("Authorization");

        String username;
        String jwtToken;

        if (token != null && token.startsWith("Bearer ")) {
            jwtToken = token.substring(7);
            try { username = tokenUtils.getUsernameFromToken(jwtToken); }
            catch (IllegalArgumentException ex) { throw new ApiError(HttpStatus.BAD_REQUEST, "Bad Authorization token."); }
            catch (ExpiredJwtException ex) { throw new ApiError(HttpStatus.BAD_REQUEST, "Authorization token expired."); }
        } else { throw new ApiError(HttpStatus.BAD_REQUEST, "Bad/Missing Authorization token at URI: " + request.getRequestURI()); }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (tokenUtils.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}

package com.spring.euler.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.euler.common.exception.ApiErrorSchema;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
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
import java.io.PrintWriter;
import java.util.List;

@Slf4j
@Component
public class RequestFilter extends OncePerRequestFilter {
    private static final List<String> AUTH_WHITELIST_EXACT = List.of(
            "/authenticate",
            "/v2/api-docs",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html");

    private static final List<String> AUTH_WHITELIST_WILDCARD = List.of("/webjars", "/swagger-resources");

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

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
            catch (Exception ex) {
                log.warn("Bad or expired token at URI: " + request.getRequestURI());
                response = setErrorResponse(response, "Bad token at URI: " + request.getRequestURI());
                return;
            }
        } else {
            log.warn("Bad or missing token at URI: " + request.getRequestURI());
            response = setErrorResponse(response, "Bad or missing token at URI: " + request.getRequestURI());
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            try {
                if (tokenUtils.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (MalformedJwtException ex) {
                log.warn("Malformed token at URI: " + request.getRequestURI());
                response = setErrorResponse(response, "Malformed token at URI: " + request.getRequestURI());
                return;
            }
            chain.doFilter(request, response);
        } else {
            log.warn("Unable to determine username from token at URI: " + request.getRequestURI());
            response = setErrorResponse(response, "Unable to determine username from token at URI: " + request.getRequestURI());
        }
    }

    private HttpServletResponse setErrorResponse(HttpServletResponse response, String message) {
        response.setStatus(401);

        try (PrintWriter writer = response.getWriter()) {
            response.setHeader("Content-Type", "application/json");
            String errorDetails = OBJECT_MAPPER.writeValueAsString(new ApiErrorSchema(HttpStatus.UNAUTHORIZED, message));
            writer.print(errorDetails);
        } catch (IOException ex) { return response; }
        return response;
    }
}

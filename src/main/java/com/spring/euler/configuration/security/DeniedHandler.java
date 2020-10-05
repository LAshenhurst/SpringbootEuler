package com.spring.euler.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.euler.common.exception.ApiErrorSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class DeniedHandler implements AccessDeniedHandler {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException deniedEx) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.warn(auth.getName() + " attempted to access URI: " + request.getRequestURI());
        response = configureDeniedResponse(response);
    }

    private HttpServletResponse configureDeniedResponse(HttpServletResponse response) {
        final String message = "This user does not have sufficient permissions to access this endpoint.";
        response.setStatus(403);

        try (PrintWriter writer = response.getWriter()) {
            response.setHeader("Content-Type", "application/json");
            String errorDetails = mapper.writeValueAsString(new ApiErrorSchema(HttpStatus.FORBIDDEN, message));
            writer.print(errorDetails);
        } catch (IOException ex) { return response; }
        return response;
    }
}

package com.spring.euler.controller;

import com.spring.euler.common.exception.ApiException;
import com.spring.euler.configuration.security.JWTUserDetailsService;
import com.spring.euler.configuration.security.TokenUtils;
import com.spring.euler.domain.security.AuthenticationRequest;
import com.spring.euler.domain.security.AuthenticationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = {"Authentication"})
public class AuthenticationController {
    private final TokenUtils tokenUtils;
    private final JWTUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    @ApiOperation(value = "Login with username and password, returns a JWT that must be in the header of all subsequent requests")
    public Mono<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = tokenUtils.generateToken(userDetails);
        log.info("Authentication success: user={}", userDetails.getUsername());
        return Mono.just(AuthenticationResponse.builder().token(token).build());
    }

    private void authenticate(String username, String password) {
        try { authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)); }
        catch (DisabledException disabledException) { throw new ApiException(HttpStatus.UNAUTHORIZED, "User Disabled."); }
        catch (BadCredentialsException badCredentialsException) { throw new ApiException(HttpStatus.UNAUTHORIZED, "Bad Credentials."); }
    }
}

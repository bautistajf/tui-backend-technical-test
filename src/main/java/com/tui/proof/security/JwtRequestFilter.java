package com.tui.proof.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtOperations jwtOperations;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.startsWith(header, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = StringUtils.substringAfter(header, " ").trim();

        if (!jwtOperations.isValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtOperations.parseUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                Optional.ofNullable(userDetails)
                        .map(UserDetails::getAuthorities)
                        .orElseGet(Collections::emptyList)
        );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}

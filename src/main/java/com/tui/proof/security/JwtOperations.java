package com.tui.proof.security;


import com.tui.proof.configuration.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@AllArgsConstructor
public class JwtOperations {

    private final JwtProperties jwtProperties;

    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return false;
    }

    public String parseUsername(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getTokenValidityInSeconds() * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }
}

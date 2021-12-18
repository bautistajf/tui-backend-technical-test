package com.tui.proof.configuration.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EnableConfigurationProperties(value = JwtProperties.class)
@SpringBootTest
class JwtPropertiesTest {

    @Autowired
    private JwtProperties jwtProperties;

    @Test
    void jwtPropertiesWasLoaded() {
        assertEquals("jwt_token_secret", jwtProperties.getSecret());
        assertEquals(720, jwtProperties.getTokenValidityInSeconds());
    }
}

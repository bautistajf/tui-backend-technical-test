package com.tui.proof.configuration.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EnableConfigurationProperties(value = OrderProperties.class)
@SpringBootTest
class OrderPropertiesTest {

    @Autowired
    private OrderProperties orderProperties;

    @Test
    void jwtPropertiesWasLoaded() {
        assertEquals(1, orderProperties.getCookTimeInSeconds());
        assertEquals(3, orderProperties.getWaitToTimeInSeconds());
    }

}

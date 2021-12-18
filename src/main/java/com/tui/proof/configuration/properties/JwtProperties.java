package com.tui.proof.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
    private String secret;

    private int tokenValidityInSeconds;
}

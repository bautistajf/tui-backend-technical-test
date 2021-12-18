package com.tui.proof.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("order")
public class OrderProperties {


    private int cookTimeInSeconds;

    private int waitToTimeInSeconds;
}

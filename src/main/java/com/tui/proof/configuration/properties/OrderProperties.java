package com.tui.proof.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties("order")
public class OrderProperties {


    private int cookTimeInSeconds;

    private int waitToTimeInSeconds;
}

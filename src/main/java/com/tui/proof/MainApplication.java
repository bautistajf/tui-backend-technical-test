package com.tui.proof;

import com.tui.proof.event.consumer.OrderConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@EnableJpaRepositories
@RequiredArgsConstructor
@SpringBootApplication
@SuppressWarnings("squid:S4823")
public class MainApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    private final OrderConsumer orderConsumer;
}

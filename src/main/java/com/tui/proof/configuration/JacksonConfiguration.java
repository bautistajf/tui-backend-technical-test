package com.tui.proof.configuration;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfiguration {

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return jackson2ObjectMapperBuilder -> {
            jackson2ObjectMapperBuilder.serializers(new LocalTimeSerializer(TIME_FORMATTER));
            jackson2ObjectMapperBuilder.deserializers(new LocalTimeDeserializer(TIME_FORMATTER));
        };
    }
}

package com.tui.proof.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonTypeName("ErrorDTO")
public class ErrorDTO {
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -5767906253263397432L;

    private List<ErrorDTO> errorDTOs;

    /**
     * Time and date the error was thrown
     */
    @Builder.Default
    private LocalDateTime dateTime = LocalDateTime.now();

    /**
     * The code.
     */
    private String code;

    /**
     * The message.
     */
    private String message;

    /**
     * The description.
     */
    private String description;

    /**
     * The name of the service that threw this error
     */
    private String serviceName;

    /**
     * The error is caused by a bad request from client, it's not due to an internal service.
     * Can be used to tell Hystrix to ignore the bad requests errors.
     */
    @Builder.Default
    private Boolean isBadRequest = false;

    /**
     * The trace ID
     */
    private String traceId;


    private String uriDoc;

    /**
     * The nested error that threw this error
     */
    private ErrorDTO nestedErrors;

    private String stackTrace;
}

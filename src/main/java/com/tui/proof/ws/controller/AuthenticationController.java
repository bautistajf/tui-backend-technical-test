package com.tui.proof.ws.controller;

import com.tui.proof.dto.AuthenticationRequest;
import com.tui.proof.dto.AuthenticationResponse;
import com.tui.proof.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.tui.proof.util.ControllerHelper.AUTH_CONTROLLER_PATH;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(AUTH_CONTROLLER_PATH)
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Authenticate user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthenticationRequest.class)
                            )
                    }),
            responses = {
                    @ApiResponse(responseCode = "200", description = "User successfully logged in",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AuthenticationResponse.class))
                            }),
                    @ApiResponse(responseCode = "401", description = "User unauthorized", content = @Content)
            }
    )
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}

package com.tui.proof.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tui.proof.dto.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.tui.proof.util.ControllerHelper.AUTH_CONTROLLER_PATH;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class AuthenticationControllerTest {

    private final ObjectMapper objectMapper;
    private final MockMvc mockMvc;


    @Test
    void successfulLogin_thenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(AUTH_CONTROLLER_PATH + "/login")
                        .content(objectMapper.writeValueAsString(new AuthenticationRequest("admin", "12345678")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.accessToken", notNullValue()));
    }

    @Test
    void wrongCredentials_thenUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(AUTH_CONTROLLER_PATH + "/login")
                        .content(objectMapper.writeValueAsString(new AuthenticationRequest("admin", "test")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}

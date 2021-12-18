package com.tui.proof.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.tui.proof.fixture.ClientFixtureBuilder.getClientDTOMock;
import static com.tui.proof.util.ControllerHelper.CLIENT_CONTROLLER_PATH;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application.yml")
@RequiredArgsConstructor
class ClientControllerTest {

    private final ObjectMapper objectMapper;
    private final MockMvc mockMvc;

    @Test
    void create_client_thenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(CLIENT_CONTROLLER_PATH)
                        .content(objectMapper.writeValueAsString(getClientDTOMock()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.clientId", notNullValue()));
    }

}

package com.tui.proof.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tui.proof.dto.OrderRequestDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.tui.proof.fixture.OrderFixtureBuilder.getRequestOrderMock;
import static com.tui.proof.fixture.OrderFixtureBuilder.getSearchOrderRequestDTOMock;
import static com.tui.proof.util.ControllerHelper.ORDER_CONTROLLER_PATH;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application.yml")
class OrderControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create_order_thenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ORDER_CONTROLLER_PATH)
                        .content(objectMapper.writeValueAsString(getRequestOrderMock()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.orderId", notNullValue()));
    }

    @Test
    void create_order_return_bad_request() throws Exception {
        final OrderRequestDTO dto = getRequestOrderMock();
        dto.setClientId(null);
        mockMvc.perform(MockMvcRequestBuilders.post(ORDER_CONTROLLER_PATH)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void update_order_thenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(ORDER_CONTROLLER_PATH + "/1")
                        .content(objectMapper.writeValueAsString(getRequestOrderMock()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.orderId", notNullValue()));
    }

    @Test
    void update_order_return_method_not_allowed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(ORDER_CONTROLLER_PATH)
                        .content(objectMapper.writeValueAsString(getRequestOrderMock()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void update_order_return_bad_request() throws Exception {
        final OrderRequestDTO dto = getRequestOrderMock();
        dto.setClientId(null);
        mockMvc.perform(MockMvcRequestBuilders.put(ORDER_CONTROLLER_PATH + "/1")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void search_order_thenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ORDER_CONTROLLER_PATH + "/search")
                        .content(objectMapper.writeValueAsString(getSearchOrderRequestDTOMock()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

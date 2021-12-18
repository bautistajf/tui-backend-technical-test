package com.tui.proof.ws.controller;

import com.tui.proof.dto.OrderDTO;
import com.tui.proof.dto.OrderRequestDTO;
import com.tui.proof.dto.SearchOrderRequestDTO;
import com.tui.proof.event.publisher.OrderPublisher;
import com.tui.proof.exception.CookedException;
import com.tui.proof.mapper.OrderMapper;
import com.tui.proof.model.Order;
import com.tui.proof.service.OrderService;
import com.tui.proof.util.ControllerHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static com.tui.proof.util.ControllerHelper.ORDER_CONTROLLER_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = ORDER_CONTROLLER_PATH, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final OrderPublisher orderPublisher;

    @Operation(summary = "Create order",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = OrderRequestDTO.class)
                            )
                    }),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Order created",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDTO.class))
                            })
            }
    )
    @PostMapping
    public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderRequestDTO orderRequest) throws InterruptedException, NotFoundException {
        final LocalDateTime startTime = LocalDateTime.now();

        final Order order = orderService.createOrder(orderMapper.toEntity(orderRequest));
        final OrderDTO response = orderMapper.toDTO(order);
        orderPublisher.publish(order);

        final HttpHeaders httpHeaders = ControllerHelper.buildHeaders(startTime);
        return new ResponseEntity<>(response, httpHeaders, HttpStatus.CREATED);
    }


    @Operation(summary = "Update order",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = OrderRequestDTO.class)
                            )
                    }),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order created",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDTO.class))
                            })
            }
    )
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(
            @PathVariable Long orderId,
            @Valid @RequestBody OrderRequestDTO orderRequest) throws NotFoundException, CookedException {
        final LocalDateTime startTime = LocalDateTime.now();

        final Order order = orderMapper.toEntity(orderRequest);
        order.setOrderId(orderId);

        final OrderDTO response = orderMapper.toDTO(orderService.updateOrder(order));

        final HttpHeaders httpHeaders = ControllerHelper.buildHeaders(startTime);
        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<OrderDTO>> seachOrders(@Valid @RequestBody SearchOrderRequestDTO search) {
        final LocalDateTime startTime = LocalDateTime.now();

        final List<OrderDTO> response = orderMapper.toListDTO(orderService.search(orderMapper.toEntity(search)));

        final HttpHeaders httpHeaders = ControllerHelper.buildHeaders(startTime);
        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }
}

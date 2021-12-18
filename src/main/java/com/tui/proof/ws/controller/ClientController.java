package com.tui.proof.ws.controller;

import com.tui.proof.dto.ClientDTO;
import com.tui.proof.dto.ClientRequestDTO;
import com.tui.proof.mapper.ClientMapper;
import com.tui.proof.model.Client;
import com.tui.proof.service.ClientService;
import com.tui.proof.util.ControllerHelper;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

import static com.tui.proof.util.ControllerHelper.CLIENT_CONTROLLER_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = CLIENT_CONTROLLER_PATH, produces = APPLICATION_JSON_VALUE)
//@Api(value = CLIENT_CONTROLLER_PATH, tags = CLIENT_TAG)
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientRequestDTO clientRequest) {
        final LocalDateTime startTime = LocalDateTime.now();

        final ClientDTO response = clientMapper.toDTO(clientService.create(clientMapper.toEntity(clientRequest)));

        final HttpHeaders httpHeaders = ControllerHelper.buildHeaders(startTime);
        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientDTO> update(
            @PathVariable Long clientId,
            @Valid @RequestBody ClientRequestDTO clientRequest) throws NotFoundException {
        final LocalDateTime startTime = LocalDateTime.now();

        final Client client = clientMapper.toEntity(clientRequest);
        client.setClientId(clientId);
        final ClientDTO response = clientMapper.toDTO(clientService.update(client));

        final HttpHeaders httpHeaders = ControllerHelper.buildHeaders(startTime);
        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }
}

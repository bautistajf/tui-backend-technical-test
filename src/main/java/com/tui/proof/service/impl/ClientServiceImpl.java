package com.tui.proof.service.impl;

import com.tui.proof.model.Client;
import com.tui.proof.repository.ClientRepository;
import com.tui.proof.service.ClientService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.tui.proof.util.ErrorMessageCode.ERROR_CLIENT_DOES_NOT_EXIST_001;
import static java.lang.String.format;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client create(final Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(final Client client) throws NotFoundException {
        final boolean exist = clientRepository.existsById(client.getClientId());

        if (exist) {
            return clientRepository.save(client);
        }

        throw new NotFoundException(format(ERROR_CLIENT_DOES_NOT_EXIST_001.getName(), client.getClientId()));
    }

    @Override
    public Client findById(final Long clientId) throws NotFoundException {
        final Optional<Client> client = clientRepository.findById(clientId);

        if (client.isEmpty()) {
            throw new NotFoundException(format(ERROR_CLIENT_DOES_NOT_EXIST_001.getName(), clientId));
        }

        return client.get();
    }
}

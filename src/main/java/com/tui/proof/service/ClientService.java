package com.tui.proof.service;


import com.tui.proof.model.Client;
import javassist.NotFoundException;

public interface ClientService {

    Client create(final Client client);

    Client update(final Client client) throws NotFoundException;

    Client findById(final Long clientId) throws NotFoundException;

}

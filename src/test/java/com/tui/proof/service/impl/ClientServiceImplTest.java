package com.tui.proof.service.impl;

import com.tui.proof.model.Client;
import com.tui.proof.repository.ClientRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.tui.proof.fixture.ClientFixtureBuilder.getClientMock;
import static com.tui.proof.fixture.ClientFixtureBuilder.getOptionalClientMock;
import static com.tui.proof.util.ErrorMessageCode.ERROR_CLIENT_DOES_NOT_EXIST_001;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl service;

    @Mock
    private ClientRepository repository;


    @Test
    void clientService_should_call_findById_repository() throws NotFoundException {
        when(repository.findById(anyLong())).thenReturn(getOptionalClientMock());

        final Client result = service.findById(1L);

        verify(repository).findById(anyLong());
        assertNotNull(result);
    }

    @Test
    void clientService_should_call_findById_repository_throw_exception() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        final Throwable exception = catchThrowable(() -> service.findById(1L));

        verify(repository).findById(anyLong());
        assertNotNull(exception);
        assertEquals(format(ERROR_CLIENT_DOES_NOT_EXIST_001.getName(), 1), exception.getMessage());
    }

    @Test
    void update_should_call_save_repository() throws NotFoundException {
        when(repository.existsById(anyLong())).thenReturn(true);
        when(repository.save(any())).thenReturn(getClientMock());

        final Client result = service.update(getClientMock());

        verify(repository).existsById(anyLong());
        verify(repository).save(any());
        assertNotNull(result);
    }

    @Test
    void update_should_throw_exception() {
        when(repository.existsById(anyLong())).thenReturn(false);

        final Throwable exception = catchThrowable(() -> service.update(getClientMock()));

        verify(repository).existsById(anyLong());
        assertNotNull(exception);
        assertEquals(format(ERROR_CLIENT_DOES_NOT_EXIST_001.getName(), 1), exception.getMessage());

    }

    @Test
    void create_should_call_save() {
        when(repository.save(any())).thenReturn(getClientMock());
        final Client result = service.create(getClientMock());

        verify(repository).save(any());
        assertNotNull(result);
    }
}

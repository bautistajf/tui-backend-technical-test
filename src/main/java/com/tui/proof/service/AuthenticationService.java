package com.tui.proof.service;


import com.tui.proof.dto.AuthenticationRequest;
import com.tui.proof.dto.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);
}

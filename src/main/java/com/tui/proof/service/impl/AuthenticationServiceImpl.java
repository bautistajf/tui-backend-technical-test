package com.tui.proof.service.impl;

import com.tui.proof.dto.AuthenticationRequest;
import com.tui.proof.dto.AuthenticationResponse;
import com.tui.proof.exception.UserLoginException;
import com.tui.proof.security.JwtOperations;
import com.tui.proof.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import static com.tui.proof.util.ErrorMessageCode.ERROR_INVALID_CREDENTIAL;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtOperations jwtOperations;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new UserLoginException(ERROR_INVALID_CREDENTIAL.getName(), e);
        }

        User user = (User) authentication.getPrincipal();
        return new AuthenticationResponse(jwtOperations.generateAccessToken(user));
    }
}

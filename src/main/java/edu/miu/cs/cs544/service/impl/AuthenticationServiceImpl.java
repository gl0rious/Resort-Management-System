package edu.miu.cs.cs544.service.impl;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.dto.request.LoginRequest;
import edu.miu.cs.cs544.dto.response.LoginResponse;
import edu.miu.cs.cs544.service.AuthenticationService;
import edu.miu.cs.cs544.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        User principal = (User) authentication.getPrincipal();
        String token = jwtUtil.issueToken(principal.getUsername(), principal.getType().toString());
        return new LoginResponse(token);
    }

}

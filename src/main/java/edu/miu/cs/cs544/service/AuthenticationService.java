package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.dto.request.LoginRequest;
import edu.miu.cs.cs544.dto.response.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);

}

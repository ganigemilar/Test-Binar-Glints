package com.backendglints.service;

import java.io.IOException;

import com.backendglints.model.request.LoginRequest;
import com.backendglints.model.request.SignUpRequest;
import com.backendglints.model.response.LoginResponse;
import com.backendglints.model.response.SignUpResponse;

public interface AuthenticationService {
	public LoginResponse login(LoginRequest login) throws IOException;
	
	public SignUpResponse signUp(SignUpRequest signUp) throws IOException;
}

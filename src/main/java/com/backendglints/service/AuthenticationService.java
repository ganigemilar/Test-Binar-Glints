package com.backendglints.service;

import java.io.IOException;

import com.backendglints.model.request.UserRequest;
import com.backendglints.model.response.Session;
import com.backendglints.model.response.UserResponse;

public interface AuthenticationService {
	public Session login(String email, String password) throws IOException;
	
	public UserResponse signUp(UserRequest user) throws IOException;
}

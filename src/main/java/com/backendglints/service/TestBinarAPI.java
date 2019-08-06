package com.backendglints.service;

import org.springframework.web.client.RestTemplate;

import com.backendglints.model.AuthLogin;

import retrofit2.Call;

public interface TestBinarAPI {
	public static final String BASE_URL = "";
	
	Call<AuthLogin> getTokenAuthentication(String email, String password);
	
}

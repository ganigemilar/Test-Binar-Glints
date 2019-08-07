package com.backendglints.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backendglints.model.request.LoginRequest;
import com.backendglints.model.request.SignUpRequest;
import com.backendglints.model.response.LoginResponse;
import com.backendglints.model.response.SignUpResponse;
import com.backendglints.service.ApiManagementService;
import com.backendglints.service.ApiManagementService.ApiVersion;
import com.backendglints.service.TestBinarService;

@RestController
public class TestBinarController {
	@Autowired
	private TestBinarService testBinarService;
	
	@PostMapping("/auth/login")
	public LoginResponse login(@RequestHeader("X-API-VERSION") String apiVersion, @RequestBody LoginRequest login) throws IOException {
		for (ApiVersion apiVer : ApiManagementService.ApiVersion.values()) {
			if (apiVer.getVersion().equals(apiVersion)) {
				return testBinarService.login(login);
			}
		}
		return new LoginResponse();
	}
	
	@PostMapping("/auth/signup")
	@ResponseBody
	public SignUpResponse signUp(
			@RequestHeader("X-API-VERSION") String apiVersion, @RequestBody SignUpRequest user) throws IOException {
		for (ApiVersion apiVer : ApiManagementService.ApiVersion.values()) {
			if (apiVer.getVersion().equals(apiVersion)) {
				return testBinarService.signUp(user);
			}
		}
		return new SignUpResponse();
	}
	
}

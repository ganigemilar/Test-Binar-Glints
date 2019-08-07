package com.backendglints.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backendglints.model.request.UserRequest;
import com.backendglints.model.response.Session;
import com.backendglints.model.response.UserResponse;
import com.backendglints.service.ApiManagementService;
import com.backendglints.service.ApiManagementService.ApiVersion;
import com.backendglints.service.TestBinarService;

@RestController
public class TestBinarController {
	@Autowired
	private TestBinarService testBinarService;
	
	@PostMapping("/auth/login")
	public Session login(
			@RequestHeader("X-API-VERSION") String apiVersion, 
			@RequestParam("email") String email, 
			@RequestParam("password") String password) throws IOException {
		for (ApiVersion apiVer : ApiManagementService.ApiVersion.values()) {
			if (apiVer.getVersion().equals(apiVersion)) {
				return testBinarService.login(email, password);
			}
		}
		return new Session();
	}
	
	@PostMapping("/auth/signup")
	public UserResponse signUp(
			@RequestHeader("X-API-VERSION") String apiVersion, @RequestBody UserRequest user) throws IOException {
		for (ApiVersion apiVer : ApiManagementService.ApiVersion.values()) {
			if (apiVer.getVersion().equals(apiVersion)) {
				return testBinarService.signUp(user);
			}
		}
		return new UserResponse();
	}
	
}

package com.backendglints.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backendglints.model.request.LoginRequest;
import com.backendglints.model.request.ProductRequest;
import com.backendglints.model.request.SignUpRequest;
import com.backendglints.model.response.LoginResponse;
import com.backendglints.model.response.ProductResponse;
import com.backendglints.model.response.SignUpResponse;
import com.backendglints.service.ApiManagementService;
import com.backendglints.service.ApiManagementService.ApiVersion;
import com.backendglints.service.TestBinarService;

@RestController
public class TestBinarController {
	@Autowired
	private TestBinarService testBinarService;
	
	@PostMapping("/auth/login")
	public LoginResponse login(@RequestHeader("X-Api-Version") String apiVersion, @RequestBody LoginRequest login) throws IOException {
		for (ApiVersion apiVer : ApiManagementService.ApiVersion.values()) {
			if (apiVer.getVersion().equals(apiVersion)) {
				return testBinarService.login(login);
			}
		}
		return new LoginResponse();
	}
	
	@PostMapping("/auth/signup")
	public SignUpResponse signUp(@RequestHeader("X-Api-Version") String apiVersion, @RequestBody SignUpRequest user) throws IOException {
		for (ApiVersion apiVer : ApiManagementService.ApiVersion.values()) {
			if (apiVer.getVersion().equals(apiVersion)) {
				return testBinarService.signUp(user);
			}
		}
		return new SignUpResponse();
	}
	
	@GetMapping("/products")
	public ProductResponse getAllProducts(@RequestHeader("X-Api-Version") String apiVersion) throws IOException {
		for (ApiVersion apiVer : ApiManagementService.ApiVersion.values()) {
			if (apiVer.getVersion().equals(apiVersion)) {
				return testBinarService.getAllProducts();
			}
		}
		return new ProductResponse();
	}
	
	@GetMapping("/products/{id}")
	public ProductResponse getProductById(@RequestHeader("X-Api-Version") String apiVersion, @PathVariable("id") String id) throws IOException {
		for (ApiVersion apiVer : ApiManagementService.ApiVersion.values()) {
			if (apiVer.getVersion().equals(apiVersion)) {
				return testBinarService.getProductById(id);
			}
		}
		return new ProductResponse();
	}
	
	@PostMapping("/products/add")
	public ProductResponse addProduct(@RequestHeader("X-Api-Version") String apiVersion, @RequestBody ProductRequest product) throws IOException {
		for (ApiVersion apiVer : ApiManagementService.ApiVersion.values()) {
			if (apiVer.getVersion().equals(apiVersion)) {
				return testBinarService.addProduct(product);
			}
		}
		return new ProductResponse();
	}
	
	@PutMapping("/products/{id}/update")
	public ProductResponse updateProduct(@RequestHeader("X-Api-Version") String apiVersion, @PathVariable("id") String id, @RequestBody ProductRequest product) throws IOException {
		for (ApiVersion apiVer : ApiManagementService.ApiVersion.values()) {
			if (apiVer.getVersion().equals(apiVersion)) {
				return testBinarService.updateProduct(id, product);
			}
		}
		return new ProductResponse();
	}
	
	@DeleteMapping("/products/{id}/delete")
	public ProductResponse deleteProductById(@RequestHeader("X-Api-Version") String apiVersion, @PathVariable("id") String id) throws IOException {
		for (ApiVersion apiVer : ApiManagementService.ApiVersion.values()) {
			if (apiVer.getVersion().equals(apiVersion)) {
				return testBinarService.deleteProductById(id);
			}
		}
		return new ProductResponse();
	}
}

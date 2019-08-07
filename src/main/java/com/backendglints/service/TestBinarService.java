package com.backendglints.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.backendglints.api.TestBinarApi.Authentication;
import com.backendglints.api.TestBinarApi.Products;
import com.backendglints.model.request.LoginRequest;
import com.backendglints.model.request.ProductRequest;
import com.backendglints.model.request.SignUpRequest;
import com.backendglints.model.response.LoginResponse;
import com.backendglints.model.response.ProductResponse;
import com.backendglints.model.response.SignUpResponse;
import com.google.gson.Gson;

@Service
public class TestBinarService implements AuthenticationService, ProductService {
	private ApiManagementService apiManagementService = new ApiManagementService();
	
	private Authentication authentication = apiManagementService.getAuthApi();
	private Products products = apiManagementService.getProductsApi();
	
	private LoginResponse session;
	
	public ApiManagementService getApiManagementService() {
		return apiManagementService;
	}

	public void setApiManagementService(ApiManagementService apiManagementService) {
		this.apiManagementService = apiManagementService;
	}
	
	public LoginResponse getSession() {
		return session;
	}

	@Override
	public LoginResponse login(LoginRequest login) throws IOException {
		LoginResponse session = authentication.login(login).execute().body();
		this.session = session;
		
		if (session != null) {
			ApiManagementService.setSession(session);
			authentication = apiManagementService.getAuthApi();
			products = apiManagementService.getProductsApi();
		}
		
		return session;
	}

	@Override
	public SignUpResponse signUp(SignUpRequest signUp) throws IOException {
		return authentication.signUp(signUp).execute().body();
	}
	
	@Override
	public ProductResponse getAllProducts() throws IOException {
		return products.showAllProducts().execute().body();
	}

	@Override
	public ProductResponse getProductById(String id) throws IOException {
		ProductResponse product = new ProductResponse();
		Gson gson = new Gson();
	  
		String responseJson = products.showProductById(id).execute().body().string();
		LinkedHashMap<String, Object> responseMap = gson.fromJson(responseJson, LinkedHashMap.class);
		String resultJson = gson.toJson(responseMap.get("result"));
		ProductResponse.Result prodRes = gson.fromJson(resultJson, ProductResponse.Result.class);
		product.setResult(Arrays.asList(prodRes));
		product.setStatus(responseMap.get("status").toString());
	  
		return product;
	}

	@Override
	public ProductResponse addProduct(ProductRequest product) throws IOException {
		return products.createProduct(product).execute().body();
	}

	@Override
	public ProductResponse updateProduct(String id, ProductRequest product) throws IOException {
		ProductResponse availableProduct = getProductById(id);
		
		if (availableProduct == null) {
			throw new IOException();
		}
		
		return products.updateProduct(id, product).execute().body();
	}

	@Override
	public ProductResponse deleteProductById(String id) throws IOException {
		return products.deleteProductById(id).execute().body();
	}
}

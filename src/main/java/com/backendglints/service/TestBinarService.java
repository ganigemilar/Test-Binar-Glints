package com.backendglints.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.backendglints.api.TestBinarApi.*;

import com.backendglints.model.request.LoginRequest;
import com.backendglints.model.request.ProductRequest;
import com.backendglints.model.request.SignUpRequest;
import com.backendglints.model.response.ProductResponse;
import com.backendglints.model.response.LoginResponse;
import com.backendglints.model.response.SignUpResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
		return session;
	}

	@Override
	public SignUpResponse signUp(SignUpRequest signUp) throws IOException {
		return authentication.signUp(signUp).execute().body();
	}
	
	@Override
	public List<ProductResponse> getAllProducts() throws IOException {
		return products.showAllProducts(session.getResult().getAccessToken()).execute().body();
	}

	@Override
	public ProductResponse getProductById(String id) throws IOException {
		return products.showProductById(session.getResult().getAccessToken(), id).execute().body();
	}

	@Override
	public ProductResponse addProduct(ProductRequest product) throws IOException {
		return products.createProduct(session.getResult().getAccessToken(), product).execute().body();
	}

	@Override
	public ProductResponse updateProduct(String id, ProductRequest product) throws IOException {
		return products.updateProduct(session.getResult().getAccessToken(), id, product).execute().body();
	}

	@Override
	public ProductResponse deleteProductById(String id) throws IOException {
		return products.deleteProductById(session.getResult().getAccessToken(), id).execute().body();
	}
}

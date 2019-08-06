package com.backendglints.service;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.backendglints.model.AuthLogin;
import com.backendglints.model.Product;
import com.backendglints.model.User;

import retrofit2.Call;

public interface TestBinarAPI {
	public static final String BASE_URL = "";

	Call<AuthLogin> login(String email, String password);

	Call<User> signUp(String name, String email, String password);

	Call<List<Product>> showAllProducts();

	Call<Product> showProductById(String id);

	Call<Product> createProduct(String name, String price, String imageUrl);

	Call<Product> updateProduct(String name);

}

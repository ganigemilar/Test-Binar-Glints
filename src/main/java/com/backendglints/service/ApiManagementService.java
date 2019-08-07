package com.backendglints.service;

import org.springframework.stereotype.Service;

import com.backendglints.api.TestBinarApi;
import com.backendglints.api.TestBinarApi.Authentication;
import com.backendglints.api.TestBinarApi.Products;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class ApiManagementService {
	private ApiVersion authApiVersion = ApiVersion.VER_1;
	private ApiVersion productsApiVersion = ApiVersion.VER_1;
	
	public static enum ApiVersion {
		VER_1	("1"),
		VER_2	("2");
		
		private String version;
		ApiVersion(String version) {
			this.version = version;
		}
		
		public String getVersion() {
			return version;
		}
	}
	
	public ApiVersion getAuthApiVersion() {
		return authApiVersion;
	}

	public void setAuthApiVersion(ApiVersion authApiVersion) {
		this.authApiVersion = authApiVersion;
	}

	public ApiVersion getProductsApiVersion() {
		return productsApiVersion;
	}

	public void setProductsApiVersion(ApiVersion productsApiVersion) {
		this.productsApiVersion = productsApiVersion;
	}
	
	private static Retrofit buildRetrofit(String baseUrl) {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		return retrofit;
	}
	
	public Authentication getAuthApi() {
		return getAuthApi(getAuthApiVersion());
	}
	
	public static Authentication getAuthApi(ApiVersion version) {
		switch (version) {
		default:
			return buildRetrofit(TestBinarApi.Authentication.BASE_URL_AUTH).create(Authentication.class);
		}
	}
	
	public Products getProductsApi() {
		return getProductsApi(getProductsApiVersion());
	}
	
	public static Products getProductsApi(ApiVersion version) {
		switch (version) {
		case VER_2:
			return buildRetrofit(TestBinarApi.BASE_URL_V2).create(Products.class);
		default:
			return buildRetrofit(TestBinarApi.BASE_URL_V1).create(Products.class);
		}
	}
}

package com.backendglints.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.backendglints.api.TestBinarApi;
import com.backendglints.api.TestBinarApi.Authentication;
import com.backendglints.api.TestBinarApi.Products;
import com.backendglints.model.response.LoginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class ApiManagementService {
	private ApiVersion authApiVersion = ApiVersion.VER_1;
	private ApiVersion productsApiVersion = ApiVersion.VER_1;
	
	private static LoginResponse session;
	
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
		GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		
		String token = session == null ? "" : session.getResult().getAccessToken();
		Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
		
		if (!token.isEmpty()) {
			retrofitBuilder.client(buildHttpClient(token));
		}
				
		retrofitBuilder.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));
		return retrofitBuilder.build();
	}
	
	private static OkHttpClient buildHttpClient(String authorization) {
		OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request().newBuilder()
						.addHeader("Authorization", authorization)
						.build();
				return chain.proceed(request);
			}
		}).build();
		return client;
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

	public static LoginResponse getSession() {
		return session;
	}

	public static void setSession(LoginResponse session) {
		ApiManagementService.session = session;
	}
}

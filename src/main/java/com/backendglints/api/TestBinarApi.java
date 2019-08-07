package com.backendglints.api;

import java.util.List;

import com.backendglints.model.request.LoginRequest;
import com.backendglints.model.request.ProductRequest;
import com.backendglints.model.request.SignUpRequest;
import com.backendglints.model.response.LoginResponse;
import com.backendglints.model.response.ProductResponse;
import com.backendglints.model.response.SignUpResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TestBinarApi {
	public static final String BASE_URL_V1 = "https://test-binar.herokuapp.com/v1/";
	public static final String BASE_URL_V2 = "https://test-binar.herokuapp.com/v2/";

	public interface Authentication {
		public static final String BASE_URL_AUTH = "https://test-binar.herokuapp.com/auth/";

		@POST("login")
		Call<LoginResponse> login(@Body LoginRequest login);

		@POST("signup")
		Call<SignUpResponse> signUp(@Body SignUpRequest signUp);
	}

	public interface Products {
		@GET("products")
		Call<ProductResponse> showAllProducts();

		@GET("products/{id}")
		Call<ResponseBody> showProductById(@Path("id") String id);

		@POST("products")
		Call<ProductResponse> createProduct(@Body ProductRequest product);

		@PUT("products/{id}")
		Call<ProductResponse> updateProduct(@Path("id") String id, @Body ProductRequest product);

		@DELETE("products/{id}")
		Call<ProductResponse> deleteProductById(@Path("id") String id);
	}
}

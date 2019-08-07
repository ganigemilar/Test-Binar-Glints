package com.backendglints.api;

import java.util.List;

import com.backendglints.model.request.ProductRequest;
import com.backendglints.model.request.UserRequest;
import com.backendglints.model.response.Session;
import com.backendglints.model.response.ProductResponse;
import com.backendglints.model.response.UserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TestBinarAPI {
	public static final String BASE_URL_AUTH = "https://test-binar.herokuapp.com/auth/";
	
	@POST("login")
	Call<Session> login(@Field("email") String email, @Field("password") String password);

	@POST("signup")
	Call<UserResponse> signUp(@Body UserRequest user);
	
	public interface Version1 {
		public static final String BASE_URL_V1 = "https://test-binar.herokuapp.com/v1/";

		@GET("products")
		Call<List<ProductResponse>> showAllProducts(@Header("Authorization") String authKey);

		@GET("products/{id}")
		Call<ProductResponse> showProductById(@Header("Authorization") String authKey, @Path("id") String id);

		@POST("products")
		Call<ProductResponse> createProduct(@Header("Authorization") String authKey, @Body ProductRequest product);

		@PUT("products/{id}")
		Call<ProductResponse> updateProduct(@Header("Authorization") String authKey, @Path("id") String id, @Body ProductRequest product);

		@DELETE("products/{id}")
		Call<ProductResponse> deleteProductById(@Header("Authorization") String authKey, @Path("id") String id);
	}
	
	public interface Version2 {
		public static final String BASE_URL_V2 = "https://test-binar.herokuapp.com/v2/";
		
		@GET("products")
		Call<ResponseBody> showAllProducts();
	}
}

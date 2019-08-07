package com.backendglints.service;

import java.io.IOException;
import java.util.List;

import com.backendglints.model.request.ProductRequest;
import com.backendglints.model.response.ProductResponse;

public interface ProductService {
	public ProductResponse getAllProducts() throws IOException;
	
	public ProductResponse getProductById(String id) throws IOException;
	
	public ProductResponse addProduct(ProductRequest product) throws IOException;
	
	public ProductResponse updateProduct(String id, ProductRequest product) throws IOException;
	
	public ProductResponse deleteProductById(String id) throws IOException;
}

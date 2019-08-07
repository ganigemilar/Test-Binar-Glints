package com.backendglints.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductRequest {
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("price")
	@Expose
	private String price;
	@SerializedName("imageurl")
	@Expose
	private String imageurl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
}

package com.backendglints.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("result")
	@Expose
	private Result result;
	@SerializedName("errors")
	@Expose
	private Errors errors;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}

	public class Result {
		@SerializedName("id")
		@Expose
		private Integer id;
		@SerializedName("name")
		@Expose
		private String name;
		@SerializedName("price")
		@Expose
		private Integer price;
		@SerializedName("imageurl")
		@Expose
		private String imageurl;
		@SerializedName("created_at")
		@Expose
		private String createdAt;
		@SerializedName("updated_at")
		@Expose
		private String updatedAt;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getPrice() {
			return price;
		}

		public void setPrice(Integer price) {
			this.price = price;
		}

		public String getImageurl() {
			return imageurl;
		}

		public void setImageurl(String imageurl) {
			this.imageurl = imageurl;
		}

		public String getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}

		public String getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(String updatedAt) {
			this.updatedAt = updatedAt;
		}
	}
	
	public class Errors {

	}
}

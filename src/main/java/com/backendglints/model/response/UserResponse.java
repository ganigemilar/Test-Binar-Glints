package com.backendglints.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
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
		@SerializedName("email")
		@Expose
		private String email;
		@SerializedName("password_digest")
		@Expose
		private String passwordDigest;
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPasswordDigest() {
			return passwordDigest;
		}

		public void setPasswordDigest(String passwordDigest) {
			this.passwordDigest = passwordDigest;
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

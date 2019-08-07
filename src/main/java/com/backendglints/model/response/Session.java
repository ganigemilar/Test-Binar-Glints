package com.backendglints.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Session {
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
		@SerializedName("access_token")
		@Expose
		private String accessToken;

		public String getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}

	}
	
	public class Errors {

	}
}

package com.infinetworks.mbayar.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerificationToken {

	@JsonProperty("verify_token")
	private String verifyToken;

	public String getVerifyToken() {
		return verifyToken;
	}

	public void setVerifyToken(String verifyToken) {
		this.verifyToken = verifyToken;
	}
}

package com.infinetworks.mbayar.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerificationRequest {

	@JsonProperty("device_id")
	private String deviceId;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}

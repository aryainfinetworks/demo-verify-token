package com.infinetworks.mbayar.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infinetworks.mbayar.demo.model.VerificationRequest;
import com.infinetworks.mbayar.demo.model.VerificationToken;
import com.infinetworks.mbayar.demo.service.VerifyTokenService;

@RestController
public class VerifyTokenController {

	@Autowired
	private VerifyTokenService verifyTokenService;

	@PostMapping("/api/devices/verify")
	public ResponseEntity<?> getVerifyToken(@RequestBody VerificationRequest request) {

		String verifyToken = this.verifyTokenService.generateVerifyToken(request.getDeviceId());

		// return
		VerificationToken retVal = new VerificationToken();
		retVal.setVerifyToken(verifyToken);

		return ResponseEntity.ok(retVal);
	}
}

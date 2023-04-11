package com.infinetworks.mbayar.demo.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;

@Service
public class VerifyTokenService {

	private static final Logger logger = LoggerFactory.getLogger(VerifyTokenService.class);

	@Value("${app.jwt.expiry}")
	private String verifyTokenExpiry;

	@Value("classpath:private.txt")
	private Resource privateKeyResource;

	public String generateVerifyToken(String deviceId) {
		try {
			PrivateKey priv = this.readPrivateKey();

			Long exp = System.currentTimeMillis() + Long.parseLong(verifyTokenExpiry) * 1000;

			Map<String, Object> cl = new HashMap<>();
			cl.put(Claims.SUBJECT, deviceId);
			cl.put("label", verifyCustomer());
			cl.put(Claims.EXPIRATION, exp);

			return Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE).setClaims(cl).signWith(priv).compact();

		} catch (Exception ex) {
			logger.error("Exception caught when generate verify token!", ex);
		}
		return null;
	}

	// ---

	private String verifyCustomer() {
		return "Customer " + UUID.randomUUID().toString();
	}

	private PrivateKey readPrivateKey() throws Exception {
		InputStream is = this.privateKeyResource.getInputStream();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
			String key = reader.lines().collect(Collectors.joining("\n"));
			String privateKeyPEM = key.replace("-----BEGIN RSA PRIVATE KEY-----", "")
					.replaceAll(System.lineSeparator(), "").replace("-----END RSA PRIVATE KEY-----", "");

			byte[] encoded = Base64.decodeBase64(privateKeyPEM);

			java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
			return keyFactory.generatePrivate(keySpec);
		}
	}
}

package com.revature.services.auth;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTClaimsSet.Builder;
import com.nimbusds.jwt.SignedJWT;
import com.revature.beans.User;

public class TokenService {
	private static final long HOUR = 3600 * 1000;

	public String generateToken(User user) throws NoSuchAlgorithmException, JOSEException, IOException {
		String encryptedToken = null;
		// Prepare JWT with claims set
		Date issueDate = new Date();
		Date expireDate = new Date(issueDate.getTime() + 12 * HOUR);
		Builder claimsBuilder = new JWTClaimsSet.Builder();
		claimsBuilder.subject("authentication token");
		claimsBuilder.issueTime(issueDate);
		claimsBuilder.expirationTime(expireDate);
		claimsBuilder.issuer("SIG's Mussab");
		claimsBuilder.claim("username", user.getUsername());

		JWTClaimsSet claims = claimsBuilder.build();
		// the implementation of getEncodedKey() is similar to the code snippet above
		// create a signed and encrypted JWT
		// the implementation of getEncodedKey() is shown above as a code snippet
		byte[] encodedKey = new KeyService().getEncodedKey();
		JWSSigner signer = new MACSigner(encodedKey);
		// create a token based on the SHA-256 hash algorithm
		SignedJWT signedToken = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claims);
		signedToken.sign(signer);
		// create JWE header that directly uses 256 bit symmetric key for block
		// encryption (AES)
		JWEHeader jweHeader = new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A256GCM).contentType("JWT")
				.build();
		// create JWE object with the signed token as the payload
		JWEObject jweObject = new JWEObject(jweHeader, new Payload(signedToken));
		// encryption the token and serialize it to get its compact form
		bypassUnlimited();
		jweObject.encrypt(new DirectEncrypter(encodedKey));
		encryptedToken = jweObject.serialize();
		return encryptedToken;
	}

	private void bypassUnlimited() {
		// hack for JCE Unlimited Strength
		try {
			Field field = Class.forName("javax.crypto.JceSecurity").getDeclaredField("isRestricted");
			field.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

			field.set(null, false);
		} catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {

		}
	}
}
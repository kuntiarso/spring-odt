package com.developer.superuser.tokenservice.core.utility;

import com.developer.superuser.tokenservice.TokenserviceConstant;
import lombok.experimental.UtilityClass;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

@UtilityClass
public class HashingUtility {
    public String withHmacSha256(String input, String secret) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec signingKey = new SecretKeySpec(secretBytes, 0, secretBytes.length, TokenserviceConstant.HMAC_SHA256_ALGORITHM);
        Mac hmacSha256Signature = Mac.getInstance(TokenserviceConstant.HMAC_SHA256_ALGORITHM);
        hmacSha256Signature.init(signingKey);
        byte[] signatureBytes = hmacSha256Signature.doFinal(input.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(signatureBytes);
    }

    public String withRsaSha256(String input, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature rsaSha256Signature = Signature.getInstance(TokenserviceConstant.RSA_SHA256_ALGORITHM);
        rsaSha256Signature.initSign(privateKey);
        rsaSha256Signature.update(input.getBytes(StandardCharsets.UTF_8));
        byte[] signatureBytes = rsaSha256Signature.sign();
        return Base64.getEncoder().encodeToString(signatureBytes);
    }
}
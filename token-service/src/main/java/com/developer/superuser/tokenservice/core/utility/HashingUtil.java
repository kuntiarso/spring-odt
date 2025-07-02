package com.developer.superuser.tokenservice.core.utility;

import com.developer.superuser.tokenservice.TokenServiceConstant;
import lombok.experimental.UtilityClass;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

@UtilityClass
public class HashingUtil {
    public String withHmacSha(String input, String secret, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec signingKey = new SecretKeySpec(secretBytes, 0, secretBytes.length, algorithm);
        Mac hmacSha = Mac.getInstance(algorithm);
        hmacSha.init(signingKey);
        byte[] signatureBytes = hmacSha.doFinal(input.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(signatureBytes);
    }

    public String withRsaSha256(String input, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature rsaSha256Signature = Signature.getInstance(TokenServiceConstant.ALGORITHM_RSA_SHA256);
        rsaSha256Signature.initSign(privateKey);
        rsaSha256Signature.update(input.getBytes(StandardCharsets.UTF_8));
        byte[] signatureBytes = rsaSha256Signature.sign();
        return Base64.getEncoder().encodeToString(signatureBytes);
    }
}
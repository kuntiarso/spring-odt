package com.developer.superuser.tokenservice.signatureresource;

import com.developer.superuser.tokenservice.core.enumeration.ApiType;
import com.developer.superuser.tokenservice.core.enumeration.SignatureType;
import com.developer.superuser.tokenservice.core.helper.GenericHelper;
import com.developer.superuser.tokenservice.core.property.DokuConfigProperties;
import com.developer.superuser.tokenservice.core.utility.HashingUtility;
import com.developer.superuser.tokenservice.signature.Signature;
import com.developer.superuser.tokenservice.signature.SignatureService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignatureHandler {
    private final SignatureService signatureService;
    private final GenericHelper<SignatureRequestDto, Void> basicSignatureValidator;
    private final GenericHelper<SignatureRequestDto, String> basicSignatureBuilder;
    private final GenericHelper<SignatureRequestDto, Void> nonSnapSignatureValidator;
    private final GenericHelper<SignatureRequestDto, String> nonSnapSignatureBuilder;
    private final SignatureCoreMapper signatureCoreMapper;
    private final DokuConfigProperties dokuConfigProperties;
    private final PrivateKey merchantPrivateKey;

    public ResponseEntity<SignatureResponseDto> generateSignature(SignatureRequestDto request) {
        try {
            Signature signature;
            log.info("Differentiating signature generation by apiType and sigType");
            if (ApiType.BASIC.equals(request.getApiType())) {
                log.info("Coming to basic signature generation");
                signature = generateBasicSignature(request);
            } else if (ApiType.NONSNAP.equals(request.getApiType())) {
                log.info("Coming to non-snap signature generation");
                signature = generateNonSnap(request);
            } else if (SignatureType.SYMMETRIC.equals(request.getSigType())) {
                log.info("Coming to symmetric signature generation");
                throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Symmetric signature generation failed");
            } else if (SignatureType.ASYMMETRIC.equals(request.getSigType())) {
                log.info("Coming to asymmetric signature generation");
                throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Asymmetric signature generation failed");
            } else {
                log.error("Unknown signature type --- {}", request.getSigType());
                throw new UnsupportedOperationException("Unsupported signature type");
            }
            return ResponseEntity.status(signature.getStatus()).body(signatureCoreMapper.mapResponse(signature));
        } catch (Exception ex) {
            log.error("Error has occurred in generateSignature handler --- {}", ex.getMessage(), ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        }
    }

    private Signature generateBasicSignature(SignatureRequestDto request) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        log.info("Checking incoming arguments for basic signature");
        basicSignatureValidator.execute(request);
        log.info("Generating basic signature");
        String stringToSign = basicSignatureBuilder.execute(request);
        log.info("Basic signature stringToSign: {}", stringToSign);
        String rsaSha256Signature = HashingUtility.withRsaSha256(stringToSign, merchantPrivateKey);
        return signatureCoreMapper.mapBasic(request, rsaSha256Signature);
    }

    private Signature generateNonSnap(SignatureRequestDto request) throws NoSuchAlgorithmException, InvalidKeyException {
        Signature signature;
        try {
            log.info("Checking incoming arguments for non-snap signature");
            nonSnapSignatureValidator.execute(request);
            log.info("Checking potential duplication of signature");
            signature = signatureService.findSignature(request.getRequestId());
            log.info("Similar signature is found and returned");
            signature.setStatus(HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            log.info("Signature not found, then generating new non-snap signature");
            String stringToSign = nonSnapSignatureBuilder.execute(request);
            log.info("Non snap signature stringToSign: {}", stringToSign);
            String hmacSha256Signature = HashingUtility.withHmacSha256(stringToSign, dokuConfigProperties.getSecretKey());
            log.info("Saving non-snap signature details");
            signature = signatureCoreMapper.mapNonSnap(request, hmacSha256Signature);
            signatureService.saveNonSnap(signature);
            log.info("Signature details is saved successfully");
            signature.setStatus(HttpStatus.CREATED);
        }
        return signature;
    }

    public String validateSignature() {
        return null;
    }
}
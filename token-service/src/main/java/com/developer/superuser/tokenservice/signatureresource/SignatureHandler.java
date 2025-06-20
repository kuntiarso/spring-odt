package com.developer.superuser.tokenservice.signatureresource;

import com.developer.superuser.tokenservice.TokenServiceConstant;
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
    private final GenericHelper<SignatureRequestDto, Void> symmetricSignatureValidator;
    private final GenericHelper<SignatureRequestDto, String> symmetricSignatureBuilder;
    private final GenericHelper<SignatureRequestDto, Void> asymmetricSignatureValidator;
    private final GenericHelper<SignatureRequestDto, String> asymmetricSignatureBuilder;
    private final SignatureCoreMapper signatureCoreMapper;
    private final DokuConfigProperties dokuConfig;
    private final PrivateKey merchantPrivateKey;

    public ResponseEntity<SignatureResponseDto> getSign(SignatureRequestDto request) {
        try {
            Signature signature;
            log.info("Differentiating signature generation by apiType and sigType");
            if (ApiType.BASIC.equals(request.getApiType())) {
                log.info("Coming to basic signature generation");
                signature = generateBasicSignature(request);
            } else if (ApiType.NONSNAP.equals(request.getApiType())) {
                log.info("Coming to non-snap signature generation");
                signature = generateNonSnapSignature(request);
            } else if (SignatureType.SYMMETRIC.equals(request.getSigType())) {
                log.info("Coming to symmetric signature generation");
                signature = generateSymmetricSignature(request);
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

    public String validateSign() {
        return null;
    }

    private Signature generateBasicSignature(SignatureRequestDto request) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        log.info("Checking incoming arguments for basic signature");
        basicSignatureValidator.execute(request);
        log.info("Generating basic signature");
        String stringToSign = basicSignatureBuilder.execute(request);
        log.info("Basic signature stringToSign: {}", stringToSign);
        String rsaSha256Signature = HashingUtility.withRsaSha256(stringToSign, merchantPrivateKey);
        Signature signature = signatureCoreMapper.mapBasic(request, stringToSign, rsaSha256Signature);
        signature.setStatus(HttpStatus.OK);
        log.info("Returning new basic signature");
        return signature;
    }

    private Signature generateNonSnapSignature(SignatureRequestDto request) throws NoSuchAlgorithmException, InvalidKeyException {
        Signature signature;
        try {
            log.info("Checking incoming arguments for non-snap signature");
            nonSnapSignatureValidator.execute(request);
            log.info("Checking duplication of non-snap signature");
            signature = signatureService.findSignature(request.getRequestId());
            log.info("Returning existing non-snap signature");
            signature.setStatus(HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            log.info("Signature not found, hence generating new non-snap signature");
            String stringToSign = nonSnapSignatureBuilder.execute(request);
            log.info("Non-snap signature stringToSign: {}", stringToSign);
            String hmacSha256Signature = HashingUtility.withHmacSha(stringToSign, dokuConfig.getApi().getKey(), TokenServiceConstant.ALGORITHM_HMAC_SHA256);
            log.info("Saving non-snap signature into db");
            signature = signatureCoreMapper.mapNonSnap(request, stringToSign, hmacSha256Signature);
            signatureService.saveNonSnap(signature);
            log.info("Successfully saved non-snap signature");
            signature.setStatus(HttpStatus.CREATED);
        }
        return signature;
    }

    private Signature generateSymmetricSignature(SignatureRequestDto request) throws NoSuchAlgorithmException, InvalidKeyException {
        Signature signature;
        try {
            log.info("Checking incoming arguments for symmetric signature");
            symmetricSignatureValidator.execute(request);
            log.info("Checking duplication of symmetric signature");
            signature = signatureService.findSignature(request.getRequestId());
            log.info("Returning existing symmetric signature");
            signature.setStatus(HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            log.info("Signature not found, hence generating new symmetric signature");
            String stringToSign = symmetricSignatureBuilder.execute(request);
            log.info("Symmetric signature stringToSign: {}", stringToSign);
            String hmacSha512Signature = HashingUtility.withHmacSha(stringToSign, dokuConfig.getApi().getKey(), TokenServiceConstant.ALGORITHM_HMAC_SHA512);
            log.info("Saving symmetric signature into db");
            signature = signatureCoreMapper.mapSymmetric(request, stringToSign, hmacSha512Signature);
            signatureService.saveSymmetric(signature);
            log.info("Successfully saved symmetric signature");
            signature.setStatus(HttpStatus.CREATED);
        }
        return signature;
    }

    private Signature generateAsymmetricSignature(SignatureRequestDto request) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Signature signature;
        try {
            log.info("Checking incoming arguments for asymmetric signature");
            asymmetricSignatureValidator.execute(request);
            log.info("Checking duplication of asymmetric signature");
            signature = signatureService.findSignature(request.getRequestId());
            log.info("Returning existing asymmetric signature");
            signature.setStatus(HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            log.info("Signature not found, hence generating new asymmetric signature");
            String stringToSign = asymmetricSignatureBuilder.execute(request);
            log.info("Asymmetric signature stringToSign: {}", stringToSign);
            String rsaSha256Signature = HashingUtility.withRsaSha256(stringToSign, merchantPrivateKey);
            log.info("Saving asymmetric signature into db");
            signature = signatureCoreMapper.mapAsymmetric(request, stringToSign, rsaSha256Signature);
            signatureService.saveAsymmetric(signature);
            log.info("Successfully saved asymmetric signature");
            signature.setStatus(HttpStatus.CREATED);
        }
        return signature;
    }
}
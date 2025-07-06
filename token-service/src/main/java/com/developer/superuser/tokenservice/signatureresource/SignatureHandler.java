package com.developer.superuser.tokenservice.signatureresource;

import com.developer.superuser.shared.data.ResponseData;
import com.developer.superuser.tokenservice.TokenServiceConstant;
import com.developer.superuser.tokenservice.core.enumeration.AlgoType;
import com.developer.superuser.tokenservice.core.enumeration.SignType;
import com.developer.superuser.tokenservice.core.helper.AsymmetricSignatureHelper;
import com.developer.superuser.tokenservice.core.helper.BasicSignatureHelper;
import com.developer.superuser.tokenservice.core.helper.NonSnapSignatureHelper;
import com.developer.superuser.tokenservice.core.helper.SymmetricSignatureHelper;
import com.developer.superuser.tokenservice.core.property.DokuConfigProperties;
import com.developer.superuser.tokenservice.core.utility.HashingUtil;
import com.developer.superuser.tokenservice.signature.Signature;
import com.developer.superuser.tokenservice.signature.SignatureService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignatureHandler {
    private final SignatureService signatureService;
    private final SignatureCoreMapper signatureCoreMapper;
    private final BasicSignatureHelper basicSignatureHelper;
    private final NonSnapSignatureHelper nonSnapSignatureHelper;
    private final SymmetricSignatureHelper symmetricSignatureHelper;
    private final AsymmetricSignatureHelper asymmetricSignatureHelper;
    private final DokuConfigProperties dokuConfig;
    private final PrivateKey merchantPrivateKey;

    public ResponseData<SignatureResponseDto> getSign(SignatureRequestDto request) {
        try {
            Signature signature;
            log.info("Getting signature based on signType and algoType");
            if (SignType.BASIC.equals(request.getSignType())) {
                log.info("Basic sign type");
                signature = generateBasicSignature(request);
            } else if (SignType.NON_SNAP.equals(request.getSignType())) {
                log.info("Non-snap sign type");
                signature = generateNonSnapSignature(request, dokuConfig.getApi().getKey());
            } else if (AlgoType.SYMMETRIC.equals(request.getAlgoType())) {
                log.info("Symmetric algo type");
                signature = generateSymmetricSignature(request, dokuConfig.getApi().getKey());
            } else if (AlgoType.ASYMMETRIC.equals(request.getAlgoType())) {
                log.info("Asymmetric algo type");
                signature = generateAsymmetricSignature(request, merchantPrivateKey);
            } else {
                log.error("Unknown sign type --- {} or algo type --- {}", request.getSignType(), request.getAlgoType());
                return ResponseData.error(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Unknown sign type or algo type");
            }
            return ResponseData.success(signatureCoreMapper.mapResponse(signature));
        } catch (Exception ex) {
            log.error("Error has occurred inside getSign handler", ex);
            return ResponseData.error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getLocalizedMessage());
        }
    }

    private Signature generateBasicSignature(SignatureRequestDto request) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        log.info("Starting to create basic signature");
        basicSignatureHelper.validate(request);
        String stringToSign = basicSignatureHelper.build(request);
        log.info("Basic signature stringToSign --- {}", stringToSign);
        String rsaSha256Signature = HashingUtil.withRsaSha256(stringToSign, merchantPrivateKey);
        Signature signature = signatureCoreMapper.mapBasic(request, stringToSign, rsaSha256Signature);
        log.info("Final basic signature --- {}", signature.getSignature());
        return signature;
    }

    private Signature generateNonSnapSignature(SignatureRequestDto request, String apiKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Signature signature;
        try {
            log.info("Starting to create non-snap signature");
            nonSnapSignatureHelper.validate(request);
            signature = signatureService.findSignature(request.getRequestId());
            log.info("Returning existing non-snap signature");
        } catch (EntityNotFoundException ex) {
            String stringToSign = nonSnapSignatureHelper.build(request);
            log.info("Non-snap signature stringToSign --- {}", stringToSign);
            String hmacSha256Signature = HashingUtil.withHmacSha(stringToSign, dokuConfig.getApi().getKey(), TokenServiceConstant.ALGORITHM_HMAC_SHA256);
            signature = signatureCoreMapper.mapNonSnap(request, stringToSign, hmacSha256Signature);
            signatureService.saveNonSnap(signature);
        }
        log.info("Final non-snap signature ---  {}", signature.getSignature());
        return signature;
    }

    private Signature generateSymmetricSignature(SignatureRequestDto request, String apiKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Signature signature;
        try {
            log.info("Starting to create symmetric signature");
            symmetricSignatureHelper.validate(request);
            signature = signatureService.findSignature(request.getRequestId());
            log.info("Returning existing symmetric signature");
        } catch (EntityNotFoundException ex) {
            String stringToSign = symmetricSignatureHelper.build(request);
            log.info("Symmetric signature stringToSign --- {}", stringToSign);
            String hmacSha512Signature = HashingUtil.withHmacSha(stringToSign, dokuConfig.getApi().getKey(), TokenServiceConstant.ALGORITHM_HMAC_SHA512);
            signature = signatureCoreMapper.mapSymmetric(request, stringToSign, hmacSha512Signature);
            signatureService.saveSymmetric(signature);
        }
        log.info("Final symmetric signature --- {}", signature.getSignature());
        return signature;
    }

    private Signature generateAsymmetricSignature(SignatureRequestDto request, PrivateKey privateKey) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Signature signature;
        try {
            log.info("Starting to create asymmetric signature");
            asymmetricSignatureHelper.validate(request);
            signature = signatureService.findSignature(request.getRequestId());
            log.info("Returning existing asymmetric signature");
        } catch (EntityNotFoundException ex) {
            String stringToSign = asymmetricSignatureHelper.build(request);
            log.info("Asymmetric signature stringToSign --- {}", stringToSign);
            String rsaSha256Signature = HashingUtil.withRsaSha256(stringToSign, privateKey);
            signature = signatureCoreMapper.mapAsymmetric(request, stringToSign, rsaSha256Signature);
            signatureService.saveAsymmetric(signature);
        }
        log.info("Final asymmetric signature --- {}", signature.getSignature());
        return signature;
    }
}
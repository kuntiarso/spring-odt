package com.developer.superuser.tokenservice.signatureresource;

import com.developer.superuser.tokenservice.core.enumeration.ApiType;
import com.developer.superuser.tokenservice.core.enumeration.SignatureType;
import com.developer.superuser.tokenservice.core.helper.GenericHelper;
import com.developer.superuser.tokenservice.core.property.DokuConfigProperties;
import com.developer.superuser.tokenservice.core.utility.HashingUtility;
import com.developer.superuser.tokenservice.signature.Signature;
import com.developer.superuser.tokenservice.signature.SignatureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignatureHandler {
    private final SignatureService signatureService;
    private final GenericHelper<SignatureRequestDto, Void> signatureNonSnapValidator;
    private final GenericHelper<SignatureRequestDto, String> signatureNonSnapBuilder;
    private final SignatureCoreMapper signatureCoreMapper;
    private final DokuConfigProperties dokuConfigProperties;

    public ResponseEntity<SignatureResponseDto> generateSignature(SignatureRequestDto request) {
        try {
            Signature signature;
            log.info("Differentiating signature generation by apiType and sigType");
            if (ApiType.NONSNAP.equals(request.getApiType())) {
                log.info("Coming to non-snap signature generation");
                signature = generateNonSnap(request);
            } else if (SignatureType.SYMMETRIC.equals(request.getSigType())) {
                log.info("Coming to symmetric signature generation");
                throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Symmetric signature generation failed");
            } else {
                log.info("Coming to asymmetric signature generation");
                throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Asymmetric signature generation failed");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(signatureCoreMapper.mapResponse(signature));
        } catch (Exception ex) {
            log.error("Error has occurred in generateSignature handler --- {}", ex.getMessage(), ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        }
    }

    private Signature generateNonSnap(SignatureRequestDto request) throws NoSuchAlgorithmException, InvalidKeyException {
        log.info("Checking incoming arguments");
        signatureNonSnapValidator.execute(request);
        log.info("Generating non-snap signature");
        String rawMaterialSignature = signatureNonSnapBuilder.execute(request);
        log.info("Raw material signature: {}", rawMaterialSignature);
        String hashedInput = HashingUtility.hashWithHmacSha256(rawMaterialSignature, dokuConfigProperties.getSecretKey());
        log.info("Saving non-snap signature details");
        Signature signature = signatureCoreMapper.mapNonSnap(request, hashedInput);
        signatureService.saveNonSnap(signature);
        log.info("Signature details is saved successfully");
        return signature;
    }

    public String validateSignature() {
        return null;
    }
}

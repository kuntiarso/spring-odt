package com.developer.superuser.tokenservice.signature;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DefaultSignatureServiceAdapter implements SignatureService {
    @Override
    public void saveNonSnap(Signature signature) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public void saveSymmetric(Signature signature) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public void saveAsymmetric(Signature signature) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public Signature findSignature(String requestId) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}
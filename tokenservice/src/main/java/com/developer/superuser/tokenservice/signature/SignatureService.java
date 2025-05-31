package com.developer.superuser.tokenservice.signature;

public interface SignatureService {
    void saveNonSnap(Signature signature);
    void saveSymmetric(Signature signature);
    void saveAsymmetric(Signature signature);
}
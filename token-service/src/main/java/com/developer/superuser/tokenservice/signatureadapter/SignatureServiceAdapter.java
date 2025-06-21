package com.developer.superuser.tokenservice.signatureadapter;

import com.developer.superuser.tokenservice.signature.Signature;
import com.developer.superuser.tokenservice.signature.SignatureService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class SignatureServiceAdapter implements SignatureService {
    private final SignatureRepository signatureRepository;
    private final SignatureEntityMapper signatureEntityMapper;

    @Override
    public void saveNonSnap(Signature signature) {
        log.info("Saving non-snap signature");
        signatureRepository.save(signatureEntityMapper.toEntityNonSnap(signature));
    }

    @Override
    public void saveSymmetric(Signature signature) {
        log.info("Saving symmetric signature");
        signatureRepository.save(signatureEntityMapper.toEntitySymmetric(signature));
    }

    @Override
    public void saveAsymmetric(Signature signature) {
        log.info("Saving asymmetric signature");
        signatureRepository.save(signatureEntityMapper.toEntityAsymmetric(signature));
    }

    @Override
    public Signature findSignature(String requestId) {
        log.info("Getting signature with requestId --- {}", requestId);
        return signatureRepository.findByRequestId(requestId)
                .map(signatureEntityMapper::toSignature)
                .orElseThrow(EntityNotFoundException::new);
    }
}
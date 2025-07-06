package com.developer.superuser.tokenservice.signatureresource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("signature")
@RequiredArgsConstructor
@Slf4j
public class SignatureController {
    private final SignatureHandler signatureHandler;

    @PostMapping("generate")
    public ResponseEntity<?> generateSignature(@RequestBody SignatureRequestDto request) {
        log.info("Request details for generateSignature --- {}", request);
        return ResponseEntity.ok(signatureHandler.getSign(request));
    }
}
package com.developer.superuser.tokenservice.tokenresource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("token")
@RequiredArgsConstructor
@Slf4j
public class TokenController {
    private final TokenHandler tokenHandler;

    @PostMapping("access")
    public ResponseEntity<?> getAccessToken(@RequestBody TokenRequestDto request) {
        log.info("Request details for getAccessToken --- {}", request);
        return ResponseEntity.ok(tokenHandler.getAccessToken(request));
    }
}
package com.developer.superuser.tokenservice.tokenresource;

import com.developer.superuser.shared.data.ResponseData;
import com.developer.superuser.tokenservice.core.data.ErrorData;
import com.developer.superuser.tokenservice.token.TokenCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    private final TokenCacheService tokenCacheService;

    @PostMapping("access")
    public ResponseEntity<?> getToken(@RequestBody TokenRequestDto request) {
        log.info("Request details for getAccessToken --- {}", request);
        ResponseData<?> response = tokenHandler.getToken(request);
        if (response.getBody() instanceof ErrorData error) {
            tokenCacheService.evictTokenB2b(request.getClientId());
            return new ResponseEntity<>(response, error.getStatus());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
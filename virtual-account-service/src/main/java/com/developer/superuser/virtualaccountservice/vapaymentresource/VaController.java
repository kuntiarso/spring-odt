package com.developer.superuser.virtualaccountservice.vapaymentresource;

import com.developer.superuser.shared.data.ResponseData;
import com.developer.superuser.virtualaccountservice.core.data.ErrorDto;
import com.developer.superuser.virtualaccountservice.vapaymentresource.dto.CreateVaRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("va")
@RequiredArgsConstructor
@Slf4j
public class VaController {
    private final VaHandler vaHandler;

    @PostMapping("create")
    public ResponseEntity<?> createVa(@Valid @RequestBody CreateVaRequest request) {
        log.info("New request for create va --- {}", request);
        ResponseData<?> response = vaHandler.createVa(request);
        if (response.getBody() instanceof ErrorDto error) {
            return new ResponseEntity<>(response, error.getStatus());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
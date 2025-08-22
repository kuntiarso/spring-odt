package com.developer.superuser.virtualaccountservice.vapaymentresource;

import com.developer.superuser.shared.data.ResponseData;
import com.developer.superuser.shared.openapi.contract.ErrorData;
import com.developer.superuser.shared.openapi.contract.VaRequest;
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
    public ResponseEntity<?> createVa(@RequestBody VaRequest request) {
        log.info("Request detail for create va --- {}", request);
        ResponseData<?> response = vaHandler.createVa(request);
        if (response.getBody() instanceof ErrorData error) {
            return new ResponseEntity<>(response, HttpStatus.valueOf(error.getStatus()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
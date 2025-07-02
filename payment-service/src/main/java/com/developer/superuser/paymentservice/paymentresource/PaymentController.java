package com.developer.superuser.paymentservice.paymentresource;

import com.developer.superuser.paymentservice.paymentresource.dto.PaymentVaRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentHandler paymentHandler;

    @PostMapping("va/create")
    public ResponseEntity<?> createPaymentVa(@Valid @RequestBody PaymentVaRequest request) {
        log.info("New incoming request for create payment va --- {}", request);
        return paymentHandler.createPaymentVa(request);
    }
}
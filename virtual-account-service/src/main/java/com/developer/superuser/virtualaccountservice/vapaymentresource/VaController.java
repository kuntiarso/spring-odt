package com.developer.superuser.virtualaccountservice.vapaymentresource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("va")
@RequiredArgsConstructor
@Slf4j
public class VaController {
    @PostMapping("create")
    public ResponseEntity<?> createVa() {
        log.info("Request details for createVa --- ");
        return ResponseEntity.ok("not implemented");
    }
}
package com.developer.superuser.virtualaccountservice.vapaymentresource;

import com.developer.superuser.virtualaccountservice.vapayment.VaPersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VaHandler {
    private final VaPersistenceService vaPersistenceService;

    public void createVa(VaRequestDto request) {
        log.info("Creating va payment");
    }
}
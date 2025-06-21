package com.developer.superuser.virtualaccountservice.vapaymentadapter.db;

import com.developer.superuser.virtualaccountservice.vapayment.VaPaymentDetail;
import com.developer.superuser.virtualaccountservice.vapayment.VaPersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class VaPersistenceServiceAdapter implements VaPersistenceService {
    private final VaRepository vaRepository;
    private final VaPaymentDetailEntityMapper vaPaymentDetailEntityMapper;

    @Override
    public void saveVa(VaPaymentDetail vaPaymentDetail) {
        log.info("Saving va payment detail");
        vaRepository.save(vaPaymentDetailEntityMapper.toEntity(vaPaymentDetail));
    }
}
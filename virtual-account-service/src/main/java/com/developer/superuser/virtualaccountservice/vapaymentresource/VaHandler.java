package com.developer.superuser.virtualaccountservice.vapaymentresource;

import com.developer.superuser.shared.data.ResponseData;
import com.developer.superuser.shared.openapi.contract.VaRequest;
import com.developer.superuser.shared.utility.Errors;
import com.developer.superuser.virtualaccountservice.vapayment.VaApiService;
import com.developer.superuser.virtualaccountservice.vapayment.VaDetail;
import com.developer.superuser.virtualaccountservice.vapayment.VaPersistenceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VaHandler {
    private final VaApiService vaApiService;
    private final VaMapper vaMapper;
    private final VaPersistenceService vaPersistenceService;

    @Transactional
    public ResponseData<?> createVa(VaRequest request) {
        try {
            log.debug("Starting to create va");
            VaDetail vaResponse = vaApiService.createVa(vaMapper.mapCore(request));
            log.info("Printing create va result --- {}", vaResponse);
            if (vaResponse.getError() != null) {
                log.error("Receiving error from create va");
                return ResponseData.error(vaResponse.getError());
            }
            vaPersistenceService.saveVa(vaResponse);
            log.debug("Successfully created va");
            return ResponseData.success(vaMapper.mapResponse(vaResponse));
        } catch (Exception ex) {
            log.error("Unknown error occurred while creating va", ex);
            return ResponseData.error(Errors.internalServerError(ex.getLocalizedMessage()));
        }
    }
}
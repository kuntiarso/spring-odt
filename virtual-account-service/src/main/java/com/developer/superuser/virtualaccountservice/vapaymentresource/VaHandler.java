package com.developer.superuser.virtualaccountservice.vapaymentresource;

import com.developer.superuser.shared.data.ResponseData;
import com.developer.superuser.virtualaccountservice.core.data.ErrorData;
import com.developer.superuser.virtualaccountservice.core.helper.SequenceNumber;
import com.developer.superuser.virtualaccountservice.vapayment.VaApiService;
import com.developer.superuser.virtualaccountservice.vapayment.VaPaymentDetail;
import com.developer.superuser.virtualaccountservice.vapayment.VaPersistenceService;
import com.developer.superuser.virtualaccountservice.vapaymentresource.dto.CreateVaRequest;
import com.developer.superuser.virtualaccountservice.vapaymentresource.mapper.VaCoreMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VaHandler {
    private final SequenceNumber sequenceNumber;
    private final VaApiService vaApiService;
    private final VaCoreMapper vaCoreMapper;
    private final VaPersistenceService vaPersistenceService;

    @Transactional
    public ResponseData<?> createVa(CreateVaRequest request) {
        try {
            String requestId = sequenceNumber.generate();
            log.info("Generated sequence number --- {}", requestId);
            VaPaymentDetail vaResponse = vaApiService.createVa(vaCoreMapper.toVaCore(requestId, request));
            log.info("Printing create va result --- {}", vaResponse);
            vaPersistenceService.saveVa(vaResponse);
            log.debug("Successfully create va");
            if (vaResponse == null) {
                return ResponseData.error(ErrorData.error(HttpStatus.INTERNAL_SERVER_ERROR, "Receiving null response from create va"));
            } else if (vaResponse.getError() != null) {
                return ResponseData.error(vaResponse.getError());
            }
            return ResponseData.success(vaCoreMapper.mapResponse(vaResponse));
        } catch (Exception ex) {
            log.error("Unknown error occurred while creating va", ex);
            return ResponseData.error(ErrorData.error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage()));
        }
    }
}
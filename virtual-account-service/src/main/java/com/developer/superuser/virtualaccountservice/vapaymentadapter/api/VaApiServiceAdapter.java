package com.developer.superuser.virtualaccountservice.vapaymentadapter.api;

import com.developer.superuser.shared.helper.Executor;
import com.developer.superuser.virtualaccountservice.VirtualAccountServiceConstant;
import com.developer.superuser.virtualaccountservice.core.property.DokuConfigProperties;
import com.developer.superuser.virtualaccountservice.core.utility.DateUtil;
import com.developer.superuser.virtualaccountservice.vapayment.VaApiService;
import com.developer.superuser.virtualaccountservice.vapayment.VaPaymentDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class VaApiServiceAdapter implements VaApiService {
    private final VaApi vaApi;
    private final DokuConfigProperties dokuConfig;
    private final Executor<Void, String> sequenceNumberGenerator;

    @Override
    public VaPaymentDetail createVa(VaPaymentDetail vaPaymentDetail) throws JsonProcessingException {
        log.info("Calling doku api for create va");
        vaPaymentDetail.getHeader().setTimestamp(DateUtil.getCurrentTimestamp());
        vaPaymentDetail.getHeader().setClientId(dokuConfig.getApi().getClientId());
        vaPaymentDetail.getHeader().setRequestId(sequenceNumberGenerator.execute());
        vaPaymentDetail.getHeader().setChannelId(VirtualAccountServiceConstant.VALUE_CHANNEL_ID);
        VaPaymentDetail responseCreateVa = vaApi.dokuCreateVa(vaPaymentDetail);
        log.info("Response from doku api for create va --- {}", responseCreateVa);
        return responseCreateVa;
    }
}
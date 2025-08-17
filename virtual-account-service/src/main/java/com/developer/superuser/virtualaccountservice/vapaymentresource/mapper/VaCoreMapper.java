package com.developer.superuser.virtualaccountservice.vapaymentresource.mapper;

import com.developer.superuser.shared.data.AmountData;
import com.developer.superuser.shared.enumeration.Currency;
import com.developer.superuser.virtualaccountservice.VirtualAccountServiceConstant;
import com.developer.superuser.virtualaccountservice.core.data.AdditionalData;
import com.developer.superuser.virtualaccountservice.core.data.HeaderData;
import com.developer.superuser.virtualaccountservice.vapayment.VaPaymentDetail;
import com.developer.superuser.virtualaccountservice.vapaymentresource.dto.CreateVaRequest;
import com.developer.superuser.virtualaccountservice.vapaymentresource.dto.CreateVaResponse;
import org.springframework.stereotype.Component;

@Component
public class VaCoreMapper {
    public VaPaymentDetail toVaCore(String requestId, CreateVaRequest request) {
        return VaPaymentDetail.builder()
                .setHeader(HeaderData.builder()
                        .setClientId(request.getHeader().getClientId())
                        .setRequestId(requestId)
                        .setTokenScheme(request.getHeader().getTokenScheme())
                        .setToken(request.getHeader().getToken())
                        .setChannelId(VirtualAccountServiceConstant.VALUE_CHANNEL_ID)
                        .build())
                .setPaymentId(request.getPaymentId())
                .setPartnerId(request.getPartnerId())
                .setCustomerNo(request.getCustomerNo())
                .setVaNo(request.getVaNo())
                .setVaName(request.getVaName())
                .setBilledAmount(AmountData.builder()
                        .setValue(request.getBilledAmount().getValue())
                        .setCurrency(Currency.valueOf(request.getBilledAmount().getCurrency()))
                        .build())
                .setAdditional(AdditionalData.builder()
                        .setChannel(request.getAdditional().getChannel())
                        .build())
                .setTransactionType(request.getTransactionType())
                .build();
    }

    public VaPaymentDetail mapDokuResponse(VaPaymentDetail va) {
        VaPaymentDetail vaDokuResponse = va.getVirtualAccountData();
        return va.toBuilder()
                .setPaymentId(vaDokuResponse.getPaymentId())
                .setPartnerId(vaDokuResponse.getPartnerId())
                .setCustomerNo(vaDokuResponse.getCustomerNo())
                .setVaNo(vaDokuResponse.getVaNo())
                .setVaName(vaDokuResponse.getVaName())
                .setBilledAmount(vaDokuResponse.getBilledAmount())
                .setTransactionType(vaDokuResponse.getTransactionType())
                .setExpiredAt(vaDokuResponse.getExpiredAt())
                .setAdditional(vaDokuResponse.getAdditional())
                .build();
    }

    public CreateVaResponse mapResponse(VaPaymentDetail va) {
        return CreateVaResponse.builder()
                .setPaymentId(va.getPaymentId())
                .setVaNo(va.getVaNo())
                .setBilledAmount(va.getBilledAmount())
                .setAdditional(va.getAdditional())
                .setExpiredDate(va.getExpiredAt())
                .build();
    }
}
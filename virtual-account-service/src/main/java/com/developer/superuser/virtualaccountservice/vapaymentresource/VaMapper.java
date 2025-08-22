package com.developer.superuser.virtualaccountservice.vapaymentresource.mapper;

import com.developer.superuser.shared.openapi.contract.AmountData;
import com.developer.superuser.shared.openapi.contract.VaRequest;
import com.developer.superuser.shared.openapi.contract.VaResponse;
import com.developer.superuser.virtualaccountservice.VirtualAccountServiceConstant;
import com.developer.superuser.virtualaccountservice.vapayment.VaPaymentDetail;
import org.springframework.stereotype.Component;

@Component
public class VaCoreMapper {
    public VaPaymentDetail mapCore(String requestId, VaRequest request) {
        return VaPaymentDetail.builder()
                .setHeader(request.getHeader().toBuilder()
                        .setRequestId(requestId)
                        .setChannelId(VirtualAccountServiceConstant.VALUE_CHANNEL_ID)
                        .build())
                .setPaymentId(request.getPaymentId())
                .setPartnerId(request.getPartnerId())
                .setRequestId(requestId)
                .setCustomerNo(request.getCustomerNo())
                .setVaNo(request.getVaNo())
                .setVaName(request.getVaName())
                .setBilledAmount(request.getBilledAmount())
                .setAdditional(request.getAdditional())
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

    public VaResponse mapResponse(VaPaymentDetail va) {
        return VaResponse.builder()
                .setPaymentId(va.getPaymentId())
                .setVaNo(va.getVaNo())
                .setBilledAmount(AmountData.builder()
                        .setValue(va.getBilledAmount().getValue())
                        .setCurrency(va.getBilledAmount().getCurrency())
                        .build())
                .setAdditional(va.getAdditional())
                .setExpiredDate(va.getExpiredAt())
                .build();
    }
}
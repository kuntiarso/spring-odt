package com.developer.superuser.virtualaccountservice.vapaymentresource;

import com.developer.superuser.shared.openapi.contract.AmountData;
import com.developer.superuser.shared.openapi.contract.VaRequest;
import com.developer.superuser.shared.openapi.contract.VaResponse;
import com.developer.superuser.virtualaccountservice.VirtualAccountServiceConstant;
import com.developer.superuser.virtualaccountservice.vapayment.VaDetail;
import org.springframework.stereotype.Component;

@Component
public class VaMapper {
    public VaDetail mapCore(VaRequest request) {
        return VaDetail.builder()
                .setHeader(request.getHeader().toBuilder()
                        .setChannelId(VirtualAccountServiceConstant.VALUE_CHANNEL_ID)
                        .build())
                .setPaymentId(request.getPaymentId())
                .setPartnerId(request.getPartnerId())
                .setRequestId(request.getHeader().getRequestId())
                .setCustomerNo(request.getCustomerNo())
                .setVaNo(request.getVaNo())
                .setVaName(request.getVaName())
                .setBilledAmount(request.getBilledAmount())
                .setAdditional(request.getAdditional())
                .setTransactionType(request.getTransactionType())
                .build();
    }

    public VaDetail mapCore(VaDetail va) {
        VaDetail vaDokuResponse = va.getVirtualAccountData();
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

    public VaResponse mapResponse(VaDetail va) {
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
package com.developer.superuser.virtualaccountservice.vapaymentresource;

import com.developer.superuser.shared.openapi.contract.VaRequest;
import com.developer.superuser.shared.openapi.contract.VaResponse;
import com.developer.superuser.virtualaccountservice.vapayment.VaDetail;
import org.springframework.stereotype.Component;

@Component
public class VaMapper {
    public VaDetail mapCore(VaRequest request) {
        return VaDetail.builder()
                .setClientId(request.getClientId())
                .setRequestId(request.getRequestId())
                .setToken(request.getToken())
                .setTokenScheme(request.getTokenScheme())
                .setPaymentId(request.getPaymentId())
                .setPartnerId(request.getPartnerId())
                .setCustomerNo(request.getCustomerNo())
                .setVaNo(request.getVaNo())
                .setVaName(request.getVaName())
                .setBilledAmount(request.getBilledAmount())
                .setTransactionType(request.getTransactionType())
                .setAdditional(request.getAdditional())
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
                .setBilledAmount(va.getBilledAmount())
                .setAdditional(va.getAdditional())
                .setExpiredDate(va.getExpiredAt())
                .build();
    }
}
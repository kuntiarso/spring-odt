package com.developer.superuser.virtualaccountservice.vapaymentadapter.db;

import com.developer.superuser.shared.embedding.Amount;
import com.developer.superuser.virtualaccountservice.core.embedding.Additional;
import com.developer.superuser.virtualaccountservice.core.embedding.AdditionalSetting;
import com.developer.superuser.virtualaccountservice.vapayment.VaPaymentDetail;
import org.springframework.stereotype.Component;

@Component
public class VaPaymentDetailEntityMapper {
    public VaPaymentDetailEntity toEntity(VaPaymentDetail vaPaymentDetail) {
        return VaPaymentDetailEntity.builder()
                .setTransactionId(vaPaymentDetail.getTransactionId())
                .setPartnerServiceId(vaPaymentDetail.getPartnerServiceId())
                .setInquiryRequestId(vaPaymentDetail.getInquiryRequestId())
                .setCustomerNo(vaPaymentDetail.getCustomerNo())
                .setVirtualAccountNo(vaPaymentDetail.getVirtualAccountNo())
                .setVirtualAccountName(vaPaymentDetail.getVirtualAccountName())
                .setBilledAmount(Amount.builder()
                        .setValue(vaPaymentDetail.getBilledAmount().getValue())
                        .setCurrency(vaPaymentDetail.getBilledAmount().getCurrency())
                        .build())
                .setAdditional(Additional.builder()
                        .setChannel(vaPaymentDetail.getAdditional().getChannel())
                        .setHowToPayPage(vaPaymentDetail.getAdditional().getHowToPayPage())
                        .setHowToPayApi(vaPaymentDetail.getAdditional().getHowToPayApi())
                        .setVirtualAccountConfig(AdditionalSetting.builder()
                                .setReusableStatus(vaPaymentDetail.getAdditional().getVirtualAccountConfig().isReusableStatus())
                                .setMinAmount(vaPaymentDetail.getAdditional().getVirtualAccountConfig().getMinAmount())
                                .setMaxAmount(vaPaymentDetail.getAdditional().getVirtualAccountConfig().getMaxAmount())
                                .build())
                        .build())
                .setTransactionType(vaPaymentDetail.getTransactionType().name().charAt(0))
                .setExpiredAt(vaPaymentDetail.getExpiredAt())
                .build();
    }
}
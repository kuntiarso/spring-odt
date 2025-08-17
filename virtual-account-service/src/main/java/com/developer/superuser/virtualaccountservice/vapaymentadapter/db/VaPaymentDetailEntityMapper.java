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
                .setPaymentId(Long.parseLong(vaPaymentDetail.getPaymentId()))
                .setPartnerId(vaPaymentDetail.getPartnerId())
                .setInquiryId(vaPaymentDetail.getInquiryId())
                .setCustomerNo(vaPaymentDetail.getCustomerNo())
                .setVaNo(vaPaymentDetail.getVaNo())
                .setVaName(vaPaymentDetail.getVaName())
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
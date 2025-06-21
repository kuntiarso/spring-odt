package com.developer.superuser.virtualaccountservice.vapayment;


import com.developer.superuser.shared.audit.StandardAuditable;
import com.developer.superuser.shared.data.AmountData;
import com.developer.superuser.virtualaccountservice.core.data.AdditionalData;
import com.developer.superuser.virtualaccountservice.core.enumeration.TransactionType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class VaPaymentDetail extends StandardAuditable {
    private Long transactionId;
    private String partnerServiceId;
    private String inquiryRequestId;
    private String customerNo;
    private String virtualAccountNo;
    private String virtualAccountName;
    private AmountData billedAmount;
    private AmountData paidAmount;
    private AdditionalData additional;
    private TransactionType transactionType;
    private Instant expiredAt;

    private String responseCode;
    private String responseMessage;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private VaPaymentDetail virtualAccountData;
}
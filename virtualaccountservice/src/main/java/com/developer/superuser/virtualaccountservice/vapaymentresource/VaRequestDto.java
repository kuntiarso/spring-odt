package com.developer.superuser.virtualaccountservice.vapaymentresource;

import com.developer.superuser.shared.data.AmountData;
import com.developer.superuser.virtualaccountservice.core.data.AdditionalData;
import com.developer.superuser.virtualaccountservice.core.enumeration.TransactionType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode
@ToString
public class VaRequestDto {
    private Long transactionId;
    private String partnerServiceId;
    private String inquiryRequestId;
    private String customerNo;
    private String virtualAccountNo;
    private String virtualAccountName;
    private AmountData totalAmount;
    private TransactionType transactionType;
    private Instant inquiryTransactionDate;
    private Instant expiredDate;
    private AdditionalData additional;
}
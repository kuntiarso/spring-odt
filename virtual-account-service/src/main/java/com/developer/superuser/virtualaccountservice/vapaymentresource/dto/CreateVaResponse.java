package com.developer.superuser.virtualaccountservice.vapaymentresource.dto;

import com.developer.superuser.shared.data.AmountData;
import com.developer.superuser.virtualaccountservice.core.data.AdditionalData;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateVaResponse {
    private String paymentId;
    private String vaNo;
    private AmountData billedAmount;
    private AdditionalData additional;
    private Instant expiredDate;
}
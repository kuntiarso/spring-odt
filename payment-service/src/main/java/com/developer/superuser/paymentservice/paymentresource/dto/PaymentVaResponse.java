package com.developer.superuser.paymentservice.paymentresource.dto;

import com.developer.superuser.paymentservice.core.dto.AdditionalDto;
import com.developer.superuser.paymentservice.core.dto.AmountDto;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PaymentVaResponse {
    @JsonProperty("paymentId")
    private Long id;
    private String vaNo;
    private AmountDto amount;
    private AdditionalDto additionalInfo;
    private Instant expiredDate;
}
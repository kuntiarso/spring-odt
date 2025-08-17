package com.developer.superuser.virtualaccountservice.vapaymentresource.dto;

import com.developer.superuser.virtualaccountservice.core.data.AdditionalData;
import com.developer.superuser.virtualaccountservice.core.data.AmountDto;
import com.developer.superuser.virtualaccountservice.core.data.HeaderData;
import com.developer.superuser.virtualaccountservice.core.enumeration.TransactionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode
@ToString
public class CreateVaRequest {
    @Valid
    @NotNull(message = "header must not be null")
    private HeaderData header;
    @NotNull(message = "requestId must not be null")
    private String paymentId;
    @NotNull(message = "partnerId must not be null")
    @NotBlank(message = "partnerId must not be empty")
    private String partnerId;
    @NotNull(message = "customerNo must not be null")
    @NotBlank(message = "customerNo must not be empty")
    private String customerNo;
    @NotNull(message = "vaNo must not be null")
    @NotBlank(message = "vaNo must not be empty")
    private String vaNo;
    @NotNull(message = "vaName must not be null")
    @NotBlank(message = "vaName must not be empty")
    private String vaName;
    @Valid
    @NotNull(message = "billedAmount must not be null")
    private AmountDto billedAmount;
    @NotNull(message = "transactionType must not be null")
    private TransactionType transactionType;
    @Valid
    @NotNull(message = "additional must not be null")
    private AdditionalData additional;
}
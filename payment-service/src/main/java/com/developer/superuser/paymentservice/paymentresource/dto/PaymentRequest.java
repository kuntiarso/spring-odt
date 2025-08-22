package com.developer.superuser.paymentservice.paymentresource.dto;

import com.developer.superuser.paymentservice.core.dto.AmountDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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
public class PaymentVaRequest {
    @NotNull(message = "orderId must not be null")
    @Min(value = 1, message = "orderId must be greater than zero")
    private Long orderId;
    @NotNull(message = "userId must not be null")
    @Min(value = 1, message = "userId must be greater than zero")
    private Long userId;
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
    private Character transactionType;
    @NotNull(message = "channel must not be null")
    @NotBlank(message = "channel must not be empty")
    private String channel;
    @NotNull(message = "paymentType must not be null")
    @NotBlank(message = "paymentType must not be empty")
    private String paymentType;
}
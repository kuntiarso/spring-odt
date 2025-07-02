package com.developer.superuser.paymentservice.core.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode
@ToString
public class AmountDto {
    @NotNull(message = "amount.value must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "amount.value must be greater than zero")
    @Digits(integer = 17, fraction = 2, message = "amount.value must be a number with max 17 digits and 2 decimal places")
    private BigDecimal value;
    @NotNull(message = "amount.currency must not be null")
    @NotBlank(message = "amount.currency must not be empty")
    private String currency;
}
package com.developer.superuser.virtualaccountservice.core.data;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode
@ToString(callSuper = true)
public class AdditionalSettingData {
    private boolean reusableStatus;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
}
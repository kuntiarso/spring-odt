package com.developer.superuser.paymentservice.payment;

import com.developer.superuser.paymentservice.core.enumeration.PaymentStatus;
import com.developer.superuser.paymentservice.core.enumeration.PaymentType;
import com.developer.superuser.shared.audit.StandardAuditable;
import com.developer.superuser.shared.data.AmountData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment extends StandardAuditable {
    private Long id;
    private Long orderId;
    private Long userId;
    private String requestId;
    private PaymentType type;
    private String gateway;
    private AmountData amount;
    private PaymentStatus status;
    private String errorCode;
    private String errorMessage;
    private Instant paidAt;
}
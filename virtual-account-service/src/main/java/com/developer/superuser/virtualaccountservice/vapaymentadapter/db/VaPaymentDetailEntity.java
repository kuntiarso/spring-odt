package com.developer.superuser.virtualaccountservice.vapaymentadapter.db;

import com.developer.superuser.shared.audit.StandardAuditableEntity;
import com.developer.superuser.shared.embedding.Amount;
import com.developer.superuser.virtualaccountservice.VirtualAccountServiceConstant;
import com.developer.superuser.virtualaccountservice.core.embedding.Additional;
import com.developer.superuser.virtualaccountservice.core.enumeration.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Table(name = VirtualAccountServiceConstant.ENTITY_VA_PAYMENT_DETAIL)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class VaPaymentDetailEntity extends StandardAuditableEntity {
    @Id
    @Column(name = "transaction_id", nullable = false, unique = true)
    private Long transactionId;

    @Column(name = "partner_service_id", nullable = false, length = 8)
    private String partnerServiceId;

    @Column(name = "inquiry_request_id", unique = true, length = 30)
    private String inquiryRequestId;

    @Column(name = "customer_no", nullable = false, length = 20)
    private String customerNo;

    @Column(name = "va_no", nullable = false, length = 28)
    private String virtualAccountNo;

    @Column(name = "va_name", nullable = false)
    private String virtualAccountName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "billed_amount_value")),
            @AttributeOverride(name = "currency", column = @Column(name = "billed_amount_currency"))
    })
    private Amount billedAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "paid_amount_value")),
            @AttributeOverride(name = "currency", column = @Column(name = "paid_amount_currency"))
    })
    private Amount paidAmount;

    @Embedded
    private Additional additional;

    @Column(name = "va_transaction_type", columnDefinition = "CHAR(1)")
    private Character transactionType;

    @Column(name = "va_expired_at")
    private Instant expiredAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", length = 16)
    private Status status;
}
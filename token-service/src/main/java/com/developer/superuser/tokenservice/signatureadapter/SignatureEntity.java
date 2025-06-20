package com.developer.superuser.tokenservice.signatureadapter;

import com.developer.superuser.shared.audit.StandardAuditableEntity;
import com.developer.superuser.tokenservice.TokenServiceConstant;
import com.developer.superuser.tokenservice.core.enumeration.ApiType;
import com.developer.superuser.tokenservice.core.enumeration.SignatureType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Table(name = TokenServiceConstant.ENTITY_SIGNATURE)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class SignatureEntity extends StandardAuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "api_type", nullable = false, length = 10)
    private ApiType apiType;

    @Enumerated(EnumType.STRING)
    @Column(name = "signature_type", length = 10)
    private SignatureType sigType;

    @Column(name = "request_id", nullable = false, unique = true, length = 50)
    private String requestId;

    @Column(name = "http_method", length = 6)
    private String httpMethod;

    @Column(name = "target_endpoint")
    private String targetEndpoint;

    @Column(name = "digest", columnDefinition = "TEXT")
    private String digest;

    @Column(name = "string_to_sign", columnDefinition = "TEXT")
    private String stringToSign;

    @Column(name = "signature", nullable = false)
    private String signature;
}
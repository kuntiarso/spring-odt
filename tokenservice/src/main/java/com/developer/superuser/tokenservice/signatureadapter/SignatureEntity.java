package com.developer.superuser.tokenservice.signatureadapter;

import com.developer.superuser.tokenservice.TokenserviceConstant;
import com.developer.superuser.tokenservice.core.enumeration.ApiType;
import com.developer.superuser.tokenservice.core.enumeration.SignatureType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = TokenserviceConstant.ENTITY_SIGNATURE)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class SignatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "api_type", nullable = false, length = 10)
    @EqualsAndHashCode.Include
    @ToString.Include
    private ApiType apiType;

    @Enumerated(EnumType.STRING)
    @Column(name = "signature_type", length = 10)
    @ToString.Include
    private SignatureType sigType;

    @Column(name = "request_id", nullable = false, unique = true, length = 50)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String requestId;

    @Column(name = "http_method", length = 6)
    @ToString.Include
    private String httpMethod;

    @Column(name = "target_endpoint")
    @ToString.Include
    private String targetEndpoint;

    @Column(name = "digest", columnDefinition = "TEXT")
    private String digest;

    @Column(name = "string_to_sign", columnDefinition = "TEXT")
    private String stringToSign;

    @Column(name = "signature", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String signature;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by", length = 50)
    private String updatedBy;
}
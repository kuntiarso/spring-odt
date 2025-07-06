package com.developer.superuser.tokenservice.signatureadapter;

import com.developer.superuser.shared.audit.StandardAuditableEntity;
import com.developer.superuser.tokenservice.TokenServiceConstant;
import com.developer.superuser.tokenservice.core.enumeration.SignType;
import com.developer.superuser.tokenservice.core.enumeration.AlgoType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpMethod;

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

    @Column(name = "request_id", nullable = false, unique = true, length = 14)
    private String requestId;

    @Enumerated(EnumType.STRING)
    @Column(name = "sign_type", nullable = false, length = 10)
    private SignType signType;

    @Enumerated(EnumType.STRING)
    @Column(name = "algo_type", length = 10)
    private AlgoType algoType;

    @Column(name = "http_method", length = 6)
    private String httpMethod;

    @Column(name = "target_endpoint")
    private String targetEndpoint;

    @Lob
    @Column(name = "digest", columnDefinition = "TEXT")
    private String digest;

    @Lob
    @Column(name = "string_to_sign", columnDefinition = "TEXT")
    private String stringToSign;

    @Column(name = "signature", nullable = false)
    private String signature;
}
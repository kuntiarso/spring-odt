package com.developer.superuser.tokenservice.signatureresource;

import com.developer.superuser.shared.utility.Dates;
import com.developer.superuser.tokenservice.core.enumeration.AlgoType;
import com.developer.superuser.tokenservice.core.enumeration.SignType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpMethod;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode
@ToString
public class SignatureRequestDto {
    private SignType signType;
    private AlgoType algoType;
    private String clientId;
    private String requestId;
    private HttpMethod httpMethod;
    private String targetEndpoint;
    private String token;
    private String digest;
    @JsonIgnore
    private final Instant timestamp = Dates.now();
}

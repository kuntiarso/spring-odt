package com.developer.superuser.paymentservice.tokensvc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Signature {
    private String apiType;
    private String sigType;
    private String clientId;
    private String requestId;
    private String httpMethod;
    private String targetEndpoint;
    private String token;
    private String digest;
    private String signature;
    private String timestamp;
}
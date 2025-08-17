package com.developer.superuser.virtualaccountservice.core.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode
@ToString(callSuper = true)
public class HeaderData {
    @NotNull(message = "clientId must not be null")
    @NotBlank(message = "clientId must not be empty")
    private String clientId;
    @NotNull(message = "tokenScheme must not be null")
    @NotBlank(message = "tokenScheme must not be empty")
    private String tokenScheme;
    @NotNull(message = "token must not be null")
    @NotBlank(message = "token must not be empty")
    private String token;
    @JsonIgnore
    private String requestId;
    @JsonIgnore
    private String channelId;
    @JsonIgnore
    private String signature;
    @JsonIgnore
    private String timestamp;
}
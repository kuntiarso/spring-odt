package com.developer.superuser.virtualaccountservice.core.data;

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
    private String timestamp;
    private String signature;
    private String clientId;
    private String requestId;
    private String channelId;
    private String token;
}
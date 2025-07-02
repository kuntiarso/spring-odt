package com.developer.superuser.paymentservice.core.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode
@ToString
public class AdditionalDto {
    private String channel;
    private String howToPayPage;
    private String howToPayApi;
}
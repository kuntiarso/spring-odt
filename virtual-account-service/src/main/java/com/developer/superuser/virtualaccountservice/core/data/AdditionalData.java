package com.developer.superuser.virtualaccountservice.core.data;

import com.developer.superuser.virtualaccountservice.core.enumeration.Channel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditionalData {
    @NotNull(message = "channel must not be null")
    private Channel channel;
    private String howToPayPage;
    private String howToPayApi;
    private AdditionalSettingData virtualAccountConfig;
}
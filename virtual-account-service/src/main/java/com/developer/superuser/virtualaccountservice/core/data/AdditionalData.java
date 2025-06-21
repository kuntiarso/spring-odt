package com.developer.superuser.virtualaccountservice.core.data;

import com.developer.superuser.virtualaccountservice.core.enumeration.Channel;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode
@ToString(callSuper = true)
public class AdditionalData {
    private Channel channel;
    private String howToPayPage;
    private String howToPayApi;
    private AdditionalSettingData virtualAccountConfig;
}
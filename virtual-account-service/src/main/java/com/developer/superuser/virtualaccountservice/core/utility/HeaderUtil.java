package com.developer.superuser.virtualaccountservice.core.utility;

import com.developer.superuser.virtualaccountservice.VirtualAccountServiceConstant;
import com.developer.superuser.virtualaccountservice.core.data.HeaderData;
import com.google.common.base.Strings;
import lombok.experimental.UtilityClass;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@UtilityClass
public class HeaderUtil {
    public MultiValueMap<String, String> multiValueMapHeader(HeaderData header) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(VirtualAccountServiceConstant.HEADER_TIMESTAMP, header.getTimestamp());
        map.add(VirtualAccountServiceConstant.HEADER_SIGNATURE, header.getSignature());
        map.add(VirtualAccountServiceConstant.HEADER_PARTNER_ID, header.getClientId());
        map.add(VirtualAccountServiceConstant.HEADER_EXTERNAL_ID, header.getRequestId());
        map.add(VirtualAccountServiceConstant.HEADER_CHANNEL_ID, header.getChannelId());
        map.add(VirtualAccountServiceConstant.HEADER_AUTHORIZATION, Strings.lenientFormat("%s %s", header.getTokenScheme(), header.getToken()));
        return map;
    }
}
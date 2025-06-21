package com.developer.superuser.virtualaccountservice.core.utility;

import com.developer.superuser.virtualaccountservice.VirtualAccountServiceConstant;
import com.developer.superuser.virtualaccountservice.core.data.HeaderData;
import lombok.experimental.UtilityClass;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@UtilityClass
public class HeaderUtil {
    public MultiValueMap<String, String> multiValueMapHeader(HeaderData headerData) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(VirtualAccountServiceConstant.HEADER_TIMESTAMP, headerData.getTimestamp());
        map.add(VirtualAccountServiceConstant.HEADER_SIGNATURE, headerData.getSignature());
        map.add(VirtualAccountServiceConstant.HEADER_PARTNER_ID, headerData.getClientId());
        map.add(VirtualAccountServiceConstant.HEADER_EXTERNAL_ID, headerData.getRequestId());
        map.add(VirtualAccountServiceConstant.HEADER_CHANNEL_ID, headerData.getChannelId());
        map.add(VirtualAccountServiceConstant.HEADER_AUTHORIZATION, headerData.getToken());
        return map;
    }
}
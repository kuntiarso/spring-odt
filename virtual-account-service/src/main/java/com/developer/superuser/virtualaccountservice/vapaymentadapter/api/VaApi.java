package com.developer.superuser.virtualaccountservice.vapaymentadapter.api;

import com.developer.superuser.virtualaccountservice.core.property.DokuConfigProperties;
import com.developer.superuser.virtualaccountservice.core.utility.HeaderUtil;
import com.developer.superuser.virtualaccountservice.vapayment.VaPaymentDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class VaApi {
    private final RestClient dokuRestClient;
    private final DokuConfigProperties dokuConfig;
    private final ObjectMapper objectMapper;

    public VaPaymentDetail dokuCreateVa(VaPaymentDetail vaPaymentDetail) throws JsonProcessingException {
        MultiValueMap<String, String> headerMap = HeaderUtil.multiValueMapHeader(vaPaymentDetail.getHeader());
        log.info("Request header for create va --- {}", objectMapper.writeValueAsString(headerMap));
        vaPaymentDetail.setHeader(null);
        log.info("Request body for create va --- {}", objectMapper.writeValueAsString(vaPaymentDetail));
        return dokuRestClient.post()
                .uri(dokuConfig.getApi().getEndpoint().get("va-create"))
                .headers(header -> header.addAll(headerMap))
                .body(vaPaymentDetail)
                .retrieve()
                .body(VaPaymentDetail.class);
    }
}
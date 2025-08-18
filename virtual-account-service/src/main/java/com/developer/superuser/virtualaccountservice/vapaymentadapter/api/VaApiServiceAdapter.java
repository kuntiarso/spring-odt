package com.developer.superuser.virtualaccountservice.vapaymentadapter.api;

import com.developer.superuser.shared.project.springodt.sign.Sign;
import com.developer.superuser.shared.project.springodt.sign.Symmetric;
import com.developer.superuser.shared.utility.Dates;
import com.developer.superuser.virtualaccountservice.vapayment.VaApiService;
import com.developer.superuser.virtualaccountservice.vapayment.VaPaymentDetail;
import com.developer.superuser.virtualaccountservice.vapaymentresource.mapper.SignMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class VaApiServiceAdapter implements VaApiService {
    private final Symmetric symmetric;
    private final SignMapper signMapper;
    private final VaApi vaApi;

    @Override
    public VaPaymentDetail createVa(VaPaymentDetail va) {
        log.debug("Calling doku api for creating va");
        Sign sign = symmetric.generate(signMapper.toSign(va));
        log.info("Printing symmetric sign result --- {}", sign);
        va.getHeader().setSignature(sign.getSignature());
        va.getHeader().setTimestamp(Dates.toInstantString(sign.getTimestamp()));
        return vaApi.dokuCreateVa(va);
    }
}
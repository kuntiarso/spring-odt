package com.developer.superuser.virtualaccountservice.core.helper;

import com.developer.superuser.shared.helper.Executor;
import com.developer.superuser.virtualaccountservice.core.data.ErrorData;
import com.developer.superuser.virtualaccountservice.vapayment.VaPaymentDetail;
import com.developer.superuser.virtualaccountservice.vapaymentresource.mapper.VaCoreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@Slf4j
public class VaApiHelper implements Executor<Supplier<VaPaymentDetail>, VaPaymentDetail> {
    private final VaCoreMapper vaCoreMapper;

    @Override
    public VaPaymentDetail execute(Supplier<VaPaymentDetail> supplier) {
        ErrorData error;
        try {
            return vaCoreMapper.mapDokuResponse(supplier.get());
        } catch (RestClientResponseException rcse) {
            log.error("Receiving unsuccessful response from doku VA api", rcse);
            HttpStatus responseStatus = HttpStatus.resolve(rcse.getStatusCode().value());
            log.error("Response status --- {}", responseStatus);
            error = rcse.getResponseBodyAs(ErrorData.class);
            if (error != null) {
                error.setStatus(responseStatus);
            } else {
                error = ErrorData.builder().setStatus(responseStatus).build();
            }
        } catch (Exception ex) {
            log.error("Unknown error has occurred while calling doku VA api", ex);
            error = ErrorData.builder().setStatus(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return VaPaymentDetail.builder().setError(error).build();
    }
}
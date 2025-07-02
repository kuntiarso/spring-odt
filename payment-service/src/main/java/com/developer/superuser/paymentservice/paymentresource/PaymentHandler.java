package com.developer.superuser.paymentservice.paymentresource;

import com.developer.superuser.paymentservice.PaymentServiceConstant;
import com.developer.superuser.paymentservice.core.enumeration.PaymentStatus;
import com.developer.superuser.paymentservice.payment.Payment;
import com.developer.superuser.paymentservice.payment.PaymentPersistenceService;
import com.developer.superuser.paymentservice.paymentresource.dto.PaymentVaRequest;
import com.developer.superuser.paymentservice.paymentresource.mapper.PaymentCoreMapper;
import com.developer.superuser.paymentservice.paymentresource.mapper.TokenSvcJsonMapper;
import com.developer.superuser.paymentservice.paymentresource.mapper.VaSvcJsonMapper;
import com.developer.superuser.paymentservice.tokensvc.TokenSvcApiService;
import com.developer.superuser.paymentservice.vasvc.VaSvcApiService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentHandler {
    private final ObjectMapper mapper;
    private final TokenSvcApiService tokenSvcApiService;
    private final TokenSvcJsonMapper tokenSvcJsonMapper;
    private final PaymentPersistenceService paymentPersistenceService;
    private final PaymentCoreMapper paymentCoreMapper;
    private final VaSvcApiService vaSvcApiService;
    private final VaSvcJsonMapper vaSvcJsonMapper;

    @Transactional
    public ResponseEntity<?> createPaymentVa(PaymentVaRequest request) {
        Payment payment = Payment.builder().build();
        String faultReason = null;
        JsonNode vaResponse = mapper.createObjectNode();
        try {
            log.info("Starting to create payment va");
            JsonNode basicSignRequest = tokenSvcJsonMapper.toSignJsonRequest(PaymentServiceConstant.SIGN_TYPE_BASIC);
            JsonNode basicSignResponse = tokenSvcApiService.getSign(basicSignRequest);
            log.info("Printing basic signature response --- {}", basicSignResponse.toString());
            JsonNode tokenRequest = tokenSvcJsonMapper.toTokenJsonRequest(basicSignResponse);
            JsonNode tokenResponse = tokenSvcApiService.getToken(tokenRequest);
            log.info("Printing token response --- {}", tokenResponse.toString());
            payment = paymentCoreMapper.toPaymentCore(request);
            payment = paymentPersistenceService.create(payment);
            log.info("Successfully created payment");
            JsonNode vaRequest = vaSvcJsonMapper.toVaJsonRequest(request, payment);
            vaResponse = vaSvcApiService.createVa(vaRequest);
            log.info("Printing va response --- {}", vaResponse.toString());
            payment.setStatus(PaymentStatus.AWAITING_PAYMENT);
        } catch (ResponseStatusException rse) {
            log.error("Reasoned error has occurred");
            faultReason = rse.getReason();
            String[] error = Strings.nullToEmpty(faultReason).split(":");
            payment.setStatus(PaymentStatus.FAILED);
            payment.setErrorCode(error[0]);
            payment.setErrorMessage(error[1]);
        } catch (Exception ex) {
            log.error("General error has occurred");
            faultReason = ex.getLocalizedMessage();
            payment.setErrorCode("500");
            payment.setErrorMessage(faultReason);
        } finally {
            payment = paymentPersistenceService.update(payment);
            log.info("Final payment transaction --- {}", payment.toString());
        }
        if (faultReason != null) {
            log.error("Printing fault reason --- {}", faultReason);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(vaSvcJsonMapper.fromVaJsonResponse(vaResponse));
    }
}
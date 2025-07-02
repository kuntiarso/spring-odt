package com.developer.superuser.paymentservice.core.helper;

import com.developer.superuser.shared.data.ResponseData;
import com.developer.superuser.shared.helper.Executor;
import com.developer.superuser.shared.utility.Predicates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OptionalOrElseThrow<A> implements Executor<ResponseData<A>, ResponseData<A>> {

    @Override
    public ResponseData<A> execute(ResponseData<A> responseData) {
        Optional.ofNullable(responseData)
                .orElseThrow(() -> exception(responseData));
        return Optional.of(responseData)
                .map(OptionalOrElseThrow::print)
                .filter(Predicates.isSuccessful2xxResponse)
                .orElseThrow(() -> exception(responseData));
    }

    private static <A> ResponseData<A> print(ResponseData<A> responseData) {
        log.info("Printing raw response data --- {}", responseData);
        return responseData;
    }

    private static <A> ResponseStatusException exception(ResponseData<A> responseData) {
        if (responseData == null) {
            log.info("Getting null response from api");
            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "");
        } else {
            log.error("Getting error response from api");
            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("%s:%s", responseData.getCode(), responseData.getMessage()));
        }
    }
}
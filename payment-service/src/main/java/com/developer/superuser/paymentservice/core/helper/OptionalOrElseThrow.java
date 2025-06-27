package com.developer.superuser.paymentservice.core.helper;

import com.developer.superuser.shared.data.ResponseData;
import com.developer.superuser.shared.helper.Executor;
import com.developer.superuser.shared.utility.Predicates;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OptionalOrElseThrow<A> implements Executor<ResponseData<A>, ResponseData<A>> {

    @Override
    public ResponseData<A> execute(ResponseData<A> responseData) {
        return Optional.ofNullable(responseData)
                .filter(Predicates.isSuccessful2xxResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
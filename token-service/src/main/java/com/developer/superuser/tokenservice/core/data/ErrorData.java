package com.developer.superuser.tokenservice.core.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode
@ToString(callSuper = true)
public class ErrorData {
    private HttpStatus status;
    @JsonProperty("responseCode")
    private String code;
    @JsonProperty("responseMessage")
    private String message;

    public static ErrorData error(HttpStatus status) {
        return new ErrorData(status, String.valueOf(status.value()), null);
    }

    public static ErrorData error(HttpStatus status, String message) {
        return new ErrorData(status, String.valueOf(status.value()), message);
    }
}
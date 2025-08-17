package com.developer.superuser.virtualaccountservice.core.data;

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
public class ErrorDto {
    private HttpStatus status;
    @JsonProperty("responseCode")
    private String code;
    @JsonProperty("responseMessage")
    private String message;

    public static ErrorDto error(HttpStatus status) {
        return new ErrorDto(status, String.valueOf(status.value()), null);
    }

    public static ErrorDto error(HttpStatus status, String message) {
        return new ErrorDto(status, String.valueOf(status.value()), message);
    }
}
package de.appsfactory.ddd.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.Pattern;

@Value
public class UserPostalCode {

    @NonNull
    @Getter
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String postcode;

    public static UserPostalCode of(String code) {
        return new UserPostalCode(code);
    }
}

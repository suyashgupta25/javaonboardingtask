package de.appsfactory.ddd.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

@Value
public class UserEmail {

    @NonNull
    @Getter
    private final String email;

    public boolean isAValidEmail() {
        return StringUtils.isNotEmpty(this.email) && EmailValidator.getInstance().isValid(this.email);
    }
}

package de.appsfactory.ddd.domain.user;

import de.appsfactory.ddd.error.exception.InvalidParameterException;
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

    public static UserEmail from(@NonNull String value) {
        if(isAValidEmail(value)) {
            return new UserEmail(value);
        } else {
            throw new InvalidParameterException(User.class, "email", value);
        }
    }

    private static boolean isAValidEmail(@NonNull String email) {
        return StringUtils.isNotEmpty(email) && EmailValidator.getInstance().isValid(email);
    }
}

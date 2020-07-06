package de.appsfactory.ddd.domain.user;

import de.appsfactory.ddd.error.exception.InvalidParameterException;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;

@Value
public class UserHomePage {

    @NonNull
    @Getter
    private final String homepage;

    public static UserHomePage from(@NonNull String value) {
        if(isAValidHomepage(value)) {
            return new UserHomePage(value);
        } else {
            throw new InvalidParameterException(User.class, "homepage", value);
        }
    }

    private static boolean isAValidHomepage(@NonNull String homepage){
        return StringUtils.isNotEmpty(homepage) && UrlValidator.getInstance().isValid(homepage);
    }
}

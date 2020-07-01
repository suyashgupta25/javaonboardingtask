package de.appsfactory.ddd.domain.user;

import de.appsfactory.ddd.error.exception.InvalidParameterException;
import de.appsfactory.ddd.util.IsoUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

@Value
public class UserCountryCode {

    @NonNull
    @Getter
    private final String countryCodeISO;

    public static UserCountryCode from(String value) {
        if(IsoUtil.isValidISOCountry(value)) {
            return new UserCountryCode(value);
        } else {
            throw new InvalidParameterException(User.class, "countryCodeISO", value);
        }
    }
}

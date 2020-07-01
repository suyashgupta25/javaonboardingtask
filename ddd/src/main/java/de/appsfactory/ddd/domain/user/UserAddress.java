package de.appsfactory.ddd.domain.user;

import de.appsfactory.ddd.error.exception.InvalidParameterException;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

@Value
public class UserAddress {

    @NonNull
    @Getter
    private final String address1;

    @NonNull
    @Getter
    private final String address2;

    @NonNull
    @Getter
    private final String address3;

    @NonNull
    @Getter
    private final UserPostalCode userPostalCode;

    @NonNull
    @Getter
    private final String city;

    @NonNull
    @Getter
    private final String countryName;

    @NonNull
    @Getter
    private final UserCountryCode countryCode;

    public static UserAddress from(@NonNull String address1, @NonNull String address2,
                                   @NonNull String address3, @NonNull UserPostalCode userPostalCode,
                                   @NonNull String city, @NonNull String countryName, @NonNull UserCountryCode countryCode) {
        if (StringUtils.isEmpty(address1) && StringUtils.isEmpty(address2) && StringUtils.isEmpty(address3)) {
            throw new InvalidParameterException(User.class, "address", "empty");
        } else if(StringUtils.isEmpty(city)) {
            throw new InvalidParameterException(User.class, "city", "empty");
        } else if(StringUtils.isEmpty(countryName)) {
            throw new InvalidParameterException(User.class, "countryName", "empty");
        } else {
            return new UserAddress(address1, address2, address3, userPostalCode, city, countryName, countryCode);
        }
    }
}

package de.appsfactory.ddd.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

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

//    public boolean isAValidAddress() {
//        return !this.firstName.isEmpty() && !this.lastName.isEmpty();
//    }

    public static UserAddress of(String address1, String address2,
                                 String address3, UserPostalCode userPostalCode,
                                 String city, String countryName) {

        return new UserAddress(address1, address2, address3, userPostalCode, city, countryName);
    }
}

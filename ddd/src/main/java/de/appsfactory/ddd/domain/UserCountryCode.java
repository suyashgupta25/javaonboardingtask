package de.appsfactory.ddd.domain;

import de.appsfactory.ddd.util.IsoUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

@Value
public class UserCountryCode {

    @NonNull
    @Getter
    private final String countryCodeISO;

    public boolean isAValidCountryCode(){
        return IsoUtil.isValidISOCountry(this.countryCodeISO);
    }
}

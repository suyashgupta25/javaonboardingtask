package de.appsfactory.ddd.domain.user;

import de.appsfactory.ddd.error.exception.InvalidParameterException;
import de.appsfactory.ddd.util.DateUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import java.util.Date;

@Value
public class UserDob {

    @NonNull
    @Getter
    private final Date dateOfBirth;

    public static UserDob from(@NonNull Date value) {
        if(DateUtil.isInThePast(value)) {
            return new UserDob(value);
        } else {
            throw new InvalidParameterException(User.class, "dateOfBirth", value.toString());
        }
    }

}

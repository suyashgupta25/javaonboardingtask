package de.appsfactory.ddd.domain.user;

import de.appsfactory.ddd.error.exception.InvalidParameterException;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

@Value
public class UserFullName {

    @NonNull
    @Getter
    private final String firstName;

    @NonNull
    @Getter
    private final String lastName;

    public static UserFullName from(@NonNull String firstName, @NonNull String lastName) {
        if (isAValidName(firstName, lastName)) {
            return new UserFullName(firstName, lastName);
        } else {
            throw new InvalidParameterException(User.class, "firstName", firstName,
                    "lastName", lastName);
        }
    }

    private static boolean isAValidName(@NonNull String firstName, @NonNull String lastName) {
        return !firstName.isEmpty() && !lastName.isEmpty();
    }
}

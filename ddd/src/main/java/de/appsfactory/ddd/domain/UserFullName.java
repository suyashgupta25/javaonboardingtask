package de.appsfactory.ddd.domain;

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

    public boolean isAValidName(){
        return !this.firstName.isEmpty() && !this.lastName.isEmpty();
    }

    public static UserFullName of(String firstName, String lastName) {
        return new UserFullName(firstName, lastName);
    }
}

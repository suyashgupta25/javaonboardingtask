package de.appsfactory.ddd.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserDob {

    @NonNull
    @Getter
    private final LocalDate dateOfBirth;

    public boolean isInThePast(){
        return this.dateOfBirth.isBefore(LocalDate.now());
    }

    public static UserDob of(LocalDate value) {
        return new UserDob(value);
    }

}

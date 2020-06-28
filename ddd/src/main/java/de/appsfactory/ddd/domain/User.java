package de.appsfactory.ddd.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    /**
     * The unique ID of the account.
     */
    @Getter
    private final UserId id;



    @Value
    public static class UserId {
        private Long value;
    }
}

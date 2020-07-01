package de.appsfactory.ddd.domain.user;

import de.appsfactory.ddd.common.SelfValidating;
import lombok.*;

import java.util.Optional;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends SelfValidating<User> {

    private final UserId id;

    @Getter
    @NonNull
    private final UserFullName userFullName;

    @Getter
    @NonNull
    private final UserDob userDob;

    @Getter
    @NonNull
    private final UserAddress userAddress;

    @Getter
    @NonNull
    private final UserEmail userEmail;

    @Getter
    private final UserHomePage userHomePage;

    public static User withId(UserId id,
                              UserFullName userFullName,
                              UserDob userDob,
                              UserAddress userAddress,
                              UserEmail userEmail,
                              UserHomePage userHomePage) {
        return new User(id, userFullName, userDob, userAddress, userEmail, userHomePage);
    }

    public static User withoutId(UserFullName userFullName,
                              UserDob userDob,
                              UserAddress userAddress,
                              UserEmail userEmail,
                              UserHomePage userHomePage) {
        return new User(null, userFullName, userDob, userAddress, userEmail, userHomePage);
    }

    public Optional<UserId> getId(){
        return Optional.ofNullable(this.id);
    }

    @Value
    public static class UserId {
        private Long value;
    }
}

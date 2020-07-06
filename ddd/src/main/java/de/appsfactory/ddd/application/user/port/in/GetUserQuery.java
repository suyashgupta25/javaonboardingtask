package de.appsfactory.ddd.application.user.port.in;

import de.appsfactory.ddd.domain.user.User;
import de.appsfactory.ddd.domain.user.User.UserId;
import org.springframework.http.ResponseEntity;

public interface GetUserQuery {

    ResponseEntity<User> getUser(UserId userId);

}

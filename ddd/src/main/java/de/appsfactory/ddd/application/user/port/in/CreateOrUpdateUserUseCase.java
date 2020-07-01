package de.appsfactory.ddd.application.user.port.in;

import de.appsfactory.ddd.domain.user.User;
import org.springframework.http.ResponseEntity;

public interface CreateOrUpdateUserUseCase {

    ResponseEntity<User> create(User user);

    ResponseEntity<User> update(User user);
}

package de.appsfactory.ddd.application.user.service;

import de.appsfactory.ddd.application.user.port.in.CreateOrUpdateUserUseCase;
import de.appsfactory.ddd.application.user.port.out.CreateOrUpdateUserPort;
import de.appsfactory.ddd.domain.user.User;
import de.appsfactory.ddd.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
class CreateOrUpdateUserService implements CreateOrUpdateUserUseCase {

    private final CreateOrUpdateUserPort createOrUpdateUserPort;

    @Override
    public ResponseEntity<User> create(User user) {
        User createdUser = createOrUpdateUserPort.create(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> update(User user) {
        if (user.getId().isPresent() && !createOrUpdateUserPort.userExists(user.getId().get())) {
            User.UserId userId = user.getId().get();
            log.error("id not found:"+userId.getValue());
            throw new EntityNotFoundException(User.class, "id", Objects.toString(userId.getValue()));
        }
        return ResponseEntity.ok(createOrUpdateUserPort.update(user));
    }
}

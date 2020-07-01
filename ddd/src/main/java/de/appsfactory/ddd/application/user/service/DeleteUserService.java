package de.appsfactory.ddd.application.user.service;

import de.appsfactory.ddd.application.user.port.in.DeleteUserUseCase;
import de.appsfactory.ddd.application.user.port.out.DeleteUserPort;
import de.appsfactory.ddd.domain.user.User;
import de.appsfactory.ddd.domain.user.User.UserId;
import de.appsfactory.ddd.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
class DeleteUserService implements DeleteUserUseCase {

    private final DeleteUserPort deleteUserPort;

    @Override
    public void deleteUser(UserId userId) {
        if (!deleteUserPort.userExists(userId)) {
            log.error("id not found:"+userId.getValue());
            throw new EntityNotFoundException(User.class, "id", Objects.toString(userId.getValue()));
        }
        deleteUserPort.deleteUser(userId);
    }
}

package de.appsfactory.ddd.application.user.port.in;

import de.appsfactory.ddd.domain.user.User;


public interface DeleteUserUseCase {
    void deleteUser(User.UserId userId);
}

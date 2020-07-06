package de.appsfactory.ddd.application.user.port.out;

import de.appsfactory.ddd.domain.user.User;
import de.appsfactory.ddd.domain.user.User.UserId;

public interface DeleteUserPort {

    void deleteUser(UserId userId);

    Boolean userExists(User.UserId userId);

}

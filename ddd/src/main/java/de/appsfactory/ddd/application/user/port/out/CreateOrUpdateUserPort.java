package de.appsfactory.ddd.application.user.port.out;

import de.appsfactory.ddd.domain.user.User;

public interface CreateOrUpdateUserPort {

    User create(User user);

    User update(User user);

    Boolean userExists(User.UserId userId);
}

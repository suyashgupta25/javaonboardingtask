package de.appsfactory.ddd.application.user.port.out;

import de.appsfactory.ddd.domain.user.User;
import de.appsfactory.ddd.domain.user.User.UserId;

public interface LoadUserPort {

    User loadUser(UserId userId);

}

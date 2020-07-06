package de.appsfactory.ddd.application.user.service;

import de.appsfactory.ddd.application.user.port.in.GetUserQuery;
import de.appsfactory.ddd.application.user.port.out.LoadUserPort;
import de.appsfactory.ddd.domain.user.User;
import de.appsfactory.ddd.domain.user.User.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class GetUserService implements GetUserQuery {

    private final LoadUserPort loadUserPort;

    @Override
    public ResponseEntity<User> getUser(UserId userId) {
        User user = loadUserPort.loadUser(userId);
        return ResponseEntity.ok(user);
    }
}

package de.appsfactory.ddd.adapter.persistence;

import de.appsfactory.ddd.application.user.port.out.CreateOrUpdateUserPort;
import de.appsfactory.ddd.application.user.port.out.DeleteUserPort;
import de.appsfactory.ddd.application.user.port.out.LoadUserPort;
import de.appsfactory.ddd.domain.user.User;
import de.appsfactory.ddd.domain.user.User.UserId;
import de.appsfactory.ddd.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@RequiredArgsConstructor
@Repository
@Slf4j
class UserJpaAdapter implements LoadUserPort, CreateOrUpdateUserPort, DeleteUserPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User loadUser(UserId userId) {
        UserJpaEntity userJpaEntity = userRepository.findById(userId.getValue())
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", Objects.toString(userId.getValue())));
        return userMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    public User create(User user) {
        UserJpaEntity userJpaEntity = userMapper.mapToJpaEntity(user);
        UserJpaEntity savedUser = userRepository.saveAndFlush(userJpaEntity);
        return userMapper.mapToDomainEntity(savedUser);
    }

    @Override
    public User update(User user) {
        UserJpaEntity userJpaEntity = userMapper.mapToJpaEntity(user);
        UserJpaEntity savedUser = userRepository.saveAndFlush(userJpaEntity);
        return userMapper.mapToDomainEntity(savedUser);
    }

    @Override
    public Boolean userExists(UserId userId) {
        return userRepository.existsById(userId.getValue());
    }

    @Override
    public void deleteUser(UserId userId) {
        userRepository.deleteById(userId.getValue());
    }
}

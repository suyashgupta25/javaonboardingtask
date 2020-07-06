package de.appsfactory.ddd.adapter.web;

import de.appsfactory.ddd.adapter.web.request.CreateUserRequest;
import de.appsfactory.ddd.adapter.web.request.UpdateUserRequest;
import de.appsfactory.ddd.application.user.port.in.CreateOrUpdateUserUseCase;
import de.appsfactory.ddd.application.user.port.in.DeleteUserUseCase;
import de.appsfactory.ddd.application.user.port.in.GetUserQuery;
import de.appsfactory.ddd.domain.user.*;
import de.appsfactory.ddd.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
class UserController {

    private final GetUserQuery getUserQuery;
    private final CreateOrUpdateUserUseCase createOrUpdateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable @Valid Long id) throws EntityNotFoundException {
        log.debug("getting a User id:"+id);
        return getUserQuery.getUser(new User.UserId(id));
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody @Valid final CreateUserRequest request) {
        log.debug("creating User={}", request);

        User user = User.withoutId(UserFullName.from(request.getFirstName(), request.getLastName()),
                UserDob.from(request.getDob()),
                UserAddress.from(request.getAddress1(), request.getAddress2(), request.getAddress3(),
                        UserPostalCode.from(request.getPostcode()), request.getCity(), request.getCountryName(),
                        UserCountryCode.from(request.getCountryISOCode())),
                UserEmail.from(request.getEmail()),
                UserHomePage.from(request.getHomepage()));

        return createOrUpdateUserUseCase.create(user);
    }

    @PutMapping(value = "/user")
    public ResponseEntity<User> updateUser(@RequestBody @Valid final UpdateUserRequest request) throws EntityNotFoundException {
        log.debug("updating User={}", request);
        User user = User.withId(new User.UserId(request.getId()),
                UserFullName.from(request.getFirstName(), request.getLastName()),
                UserDob.from(request.getDob()),
                UserAddress.from(request.getAddress1(), request.getAddress2(), request.getAddress3(),
                        UserPostalCode.from(request.getPostcode()), request.getCity(), request.getCountryName(),
                        UserCountryCode.from(request.getCountryISOCode())),
                UserEmail.from(request.getEmail()),
                UserHomePage.from(request.getHomepage()));
        return createOrUpdateUserUseCase.update(user);
    }

    @DeleteMapping(value = "/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable @Valid Long id) throws EntityNotFoundException {
        log.debug("deleting User");
        deleteUserUseCase.deleteUser(new User.UserId(id));
    }

}

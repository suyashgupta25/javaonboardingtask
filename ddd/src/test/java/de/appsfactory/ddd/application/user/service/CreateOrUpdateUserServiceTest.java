package de.appsfactory.ddd.application.user.service;

import de.appsfactory.ddd.application.user.port.out.CreateOrUpdateUserPort;
import de.appsfactory.ddd.domain.user.User;
import de.appsfactory.ddd.util.DummyObjects;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class CreateOrUpdateUserServiceTest {

    private final CreateOrUpdateUserPort createOrUpdateUserPort =
            Mockito.mock(CreateOrUpdateUserPort.class);

    private final CreateOrUpdateUserService createOrUpdateUserService =
            new CreateOrUpdateUserService(createOrUpdateUserPort);

    @Before
    public void setUp() {
        Assert.assertNotNull("createOrUpdateUserPort is not set", createOrUpdateUserPort);
    }

    @Test
    public void whenCreateUser_thenUserShouldBeCreated() {
        User user = DummyObjects.getUserWithoutId();
        Mockito.when(createOrUpdateUserPort.create(any()))
                .thenReturn(user);

        ResponseEntity<User> found = createOrUpdateUserService.create(user);
        assertNotNull(found.getBody());
        assertEquals(found.getBody().getUserEmail().getEmail(), user.getUserEmail().getEmail());
    }

    @Test
    public void whenUpdateUser_thenUserShouldBeUpdated() {
        User user = DummyObjects.getUserWithoutId();
        Mockito.when(createOrUpdateUserPort.update(any()))
                .thenReturn(user);

        ResponseEntity<User> found = createOrUpdateUserService.update(user);
        assertNotNull(found.getBody());
        assertEquals(found.getBody().getUserEmail().getEmail(), user.getUserEmail().getEmail());
    }

}

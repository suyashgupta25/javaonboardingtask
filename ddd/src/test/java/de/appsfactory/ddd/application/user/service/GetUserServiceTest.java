package de.appsfactory.ddd.application.user.service;

import de.appsfactory.ddd.application.user.port.out.LoadUserPort;
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
public class GetUserServiceTest {

    private final LoadUserPort loadUserPort =
            Mockito.mock(LoadUserPort.class);

    private final GetUserService getUserService =
            new GetUserService(loadUserPort);

    @Before
    public void setUp() {
        Assert.assertNotNull("loadUserPort is not set", loadUserPort);
    }

    @Test
    public void whenGetUser_thenUserShouldBeFetched() {
        User user = DummyObjects.getUserWithoutId();
        Mockito.when(loadUserPort.loadUser(any()))
                .thenReturn(user);

        ResponseEntity<User> found = getUserService.getUser(new User.UserId(1L));
        assertNotNull(found.getBody());
        assertEquals(found.getBody().getUserEmail().getEmail(), user.getUserEmail().getEmail());
    }

}

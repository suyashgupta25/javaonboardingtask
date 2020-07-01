package de.appsfactory.ddd.application.user.service;

import de.appsfactory.ddd.application.user.port.out.DeleteUserPort;
import de.appsfactory.ddd.domain.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class DeleteUserServiceTest {

    private final DeleteUserPort deleteUserPort =
            Mockito.mock(DeleteUserPort.class);

    private final DeleteUserService deleteUserService =
            new DeleteUserService(deleteUserPort);

    @Before
    public void setUp() {
        Assert.assertNotNull("deleteUserPort is not set", deleteUserPort);
    }

    @Test
    public void whenDeleteUser_thenUserShouldBeDeleted() {
        Mockito.doNothing().when(deleteUserPort).deleteUser(any());
        Mockito.doReturn(true).when(deleteUserPort).userExists(any());
        deleteUserService.deleteUser(new User.UserId(1L));
    }

}

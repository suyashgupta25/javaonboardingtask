package de.appsfactory.ddd.adapter.web;

import de.appsfactory.ddd.adapter.web.request.CreateUserRequest;
import de.appsfactory.ddd.adapter.web.request.UpdateUserRequest;
import de.appsfactory.ddd.application.user.port.in.CreateOrUpdateUserUseCase;
import de.appsfactory.ddd.application.user.port.in.DeleteUserUseCase;
import de.appsfactory.ddd.application.user.port.in.GetUserQuery;
import de.appsfactory.ddd.domain.user.User;
import de.appsfactory.ddd.util.DummyObjects;
import de.appsfactory.ddd.util.JsonUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GetUserQuery getUserQuery;
    @MockBean
    private CreateOrUpdateUserUseCase createOrUpdateUserUseCase;
    @MockBean
    private DeleteUserUseCase deleteUserUseCase;


    @Before
    public void setUp() {
        Assert.assertNotNull("GetUserQuery is not set", getUserQuery);
        Assert.assertNotNull("CreateOrUpdateUserUseCase is not set", createOrUpdateUserUseCase);
        Assert.assertNotNull("DeleteUserUseCase is not set", deleteUserUseCase);
    }

    @Test
    public void whenGetUser_thenReturnJson()
            throws Exception {
        User user = DummyObjects.getUserWithId();
        given(getUserQuery.getUser(any())).willReturn(ResponseEntity.ok(user));

        mvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userFullName.firstName", is(user.getUserFullName().getFirstName())));
    }

    @Test
    public void whenAddUser_thenReturnJson()
            throws Exception {
        User user = DummyObjects.getUserWithId();
        given(createOrUpdateUserUseCase.create(any())).willReturn(ResponseEntity.ok(user));
        CreateUserRequest createUser = DummyObjects.getCreateUser();
        mvc.perform(post("/user")
                .content(JsonUtil.toJson(createUser))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userFullName.firstName", is(user.getUserFullName().getFirstName())));
    }

    @Test
    public void whenUpdateUser_thenReturnJson()
            throws Exception {
        User user = DummyObjects.getUserWithId();
        given(createOrUpdateUserUseCase.update(any())).willReturn(ResponseEntity.ok(user));
        UpdateUserRequest updateUser = DummyObjects.getUpdateUser();
        mvc.perform(put("/user")
                .content(JsonUtil.toJson(updateUser))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userFullName.firstName", is(user.getUserFullName().getFirstName())));
    }

    @Test
    public void whenDeleteUser_returnSuccess()
            throws Exception {
        doNothing().when(deleteUserUseCase).deleteUser(any());

        mvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

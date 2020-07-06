package de.appsfactory.ddd.integration;

import de.appsfactory.ddd.DDDApplication;
import de.appsfactory.ddd.adapter.web.request.CreateUserRequest;
import de.appsfactory.ddd.adapter.web.request.UpdateUserRequest;
import de.appsfactory.ddd.util.DummyObjects;
import de.appsfactory.ddd.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DDDApplication.class)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Sql(scripts={"/de/appsfactory/ddd/adapter/persistence/UserPersistenceAdapterTest.sql"})
    public void whenGetUser_thenStatus200() throws Exception {
        mvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userEmail.email", is("test@test.com")));
    }

    @Test
    @Sql(scripts={"/de/appsfactory/ddd/adapter/persistence/ClearUserTable.sql"})
    public void whenCreateUser_thenStatus201() throws Exception {
        CreateUserRequest createUserRequest = DummyObjects.getCreateUser();

        mvc.perform(post("/user").content(JsonUtil.toJson(createUserRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userEmail.email", is(createUserRequest.getEmail())));
    }

    @Test
    @Sql(scripts={"/de/appsfactory/ddd/adapter/persistence/UserPersistenceAdapterTest.sql"})
    public void whenUpdateUser_thenStatus200() throws Exception {
        UpdateUserRequest updateUserRequest = DummyObjects.getUpdateUser();

        mvc.perform(put("/user").content(JsonUtil.toJson(updateUserRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userEmail.email", is(updateUserRequest.getEmail())));
    }

    @Test
    @Sql(scripts={"/de/appsfactory/ddd/adapter/persistence/UserPersistenceAdapterTest.sql"})
    public void whenDeleteUser_thenStatus200() throws Exception {
        mvc.perform(delete("/user/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

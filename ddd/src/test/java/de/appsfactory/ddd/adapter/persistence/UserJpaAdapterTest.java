package de.appsfactory.ddd.adapter.persistence;

import de.appsfactory.ddd.domain.user.User;
import de.appsfactory.ddd.util.DummyObjects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({UserJpaAdapter.class, UserMapper.class})
class UserJpaAdapterTest {

    @Autowired
    private UserJpaAdapter adapterUnderTest;

    @Test
    @Sql(scripts={"UserPersistenceAdapterTest.sql"})
    void whenLoadingAUser_userIsFetchedFromDB() {
        User user = adapterUnderTest.loadUser(new User.UserId(1L));
        User updated = adapterUnderTest.update(user);
        assertThat(updated.getUserFullName().getFirstName()).isEqualTo("Jack");
        assertThat(updated.getUserAddress().getUserPostalCode().getPostcode()).isEqualTo("045582");
    }

    @Test
    @Sql(scripts={"UserPersistenceAdapterTest.sql"})
    void whenDeletingAUser_userIsDeletedFromDB() {
        adapterUnderTest.deleteUser(new User.UserId(1L));
    }

    @Test
    void whenCreatingAUser_userIsCreatedInDB() {
        User user = adapterUnderTest.create(DummyObjects.getUserWithoutId());
        assertThat(user.getUserEmail().getEmail()).isEqualTo("test@test.com");
    }

    @Test
    void whenUpdatingAUser_userIsUpdatedInDB() {
        User user = adapterUnderTest.update(DummyObjects.getUserWithId());
        assertThat(user.getUserEmail().getEmail()).isEqualTo("test@test.com");
    }
}

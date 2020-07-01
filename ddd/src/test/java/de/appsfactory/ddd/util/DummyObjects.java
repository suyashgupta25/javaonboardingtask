package de.appsfactory.ddd.util;

import de.appsfactory.ddd.adapter.web.request.CreateUserRequest;
import de.appsfactory.ddd.adapter.web.request.UpdateUserRequest;
import de.appsfactory.ddd.domain.user.*;

import java.util.Calendar;

public class DummyObjects {
    private static final String TEST_FIRST_NAME = "firstName";

    public static User getUserWithoutId() {
        Calendar dob = Calendar.getInstance();
        dob.set(1990, 11, 31, 59, 59, 59);
        User user = User.withoutId(UserFullName.from(TEST_FIRST_NAME, "last"),
                UserDob.from(dob.getTime()),
                UserAddress.from("add1", "add2", "add3",
                        UserPostalCode.from("034442"), "Leipzig", "Germany",
                        UserCountryCode.from("DE")),
                UserEmail.from("test@test.com"),
                UserHomePage.from("https://www.test.com"));
        return user;
    }

    public static User getUserWithId() {
        Calendar dob = Calendar.getInstance();
        dob.set(1990, 11, 31, 59, 59, 59);
        User user = User.withId(new User.UserId(1L),
                UserFullName.from(TEST_FIRST_NAME, "last"),
                UserDob.from(dob.getTime()),
                UserAddress.from("add1", "add2", "add3",
                        UserPostalCode.from("034442"), "Leipzig", "Germany",
                        UserCountryCode.from("DE")),
                UserEmail.from("test@test.com"),
                UserHomePage.from("https://www.test.com"));
        return user;
    }

    public static CreateUserRequest getCreateUser() {
        Calendar dob = Calendar.getInstance();
        dob.set(1990, 11, 31, 59, 59, 59);
        CreateUserRequest request = new CreateUserRequest(TEST_FIRST_NAME, "lastName",
                dob.getTime(), "add1", "add2", "add3",
                "25554", "Leipzig", "germany", "DE", "test@test.com",
                "https://www.test.com");
        return request;
    }

    public static UpdateUserRequest getUpdateUser() {
        Calendar dob = Calendar.getInstance();
        dob.set(1990, 11, 31, 59, 59, 59);
        UpdateUserRequest request = new UpdateUserRequest(1L, TEST_FIRST_NAME, "lastName",
                dob.getTime(), "add1", "add2", "add3",
                "25554", "Leipzig", "germany", "DE", "test@test.com",
                "https://www.test.com");
        return request;
    }

}

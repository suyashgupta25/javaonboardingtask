package de.appsfactory.ddd.adapter.web.request;

import de.appsfactory.ddd.common.SelfValidating;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class UpdateUserRequest extends SelfValidating<UpdateUserRequest> {
    @NonNull
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date dob;

    @NonNull
    private String address1;

    @NonNull
    private String address2;

    @NonNull
    private String address3;

    @NonNull
    private String postcode;

    @NonNull
    private String city;

    @NonNull
    private String countryName;

    @NonNull
    private String countryISOCode;

    @NonNull
    private String email;

    @NonNull
    private String homepage;
}

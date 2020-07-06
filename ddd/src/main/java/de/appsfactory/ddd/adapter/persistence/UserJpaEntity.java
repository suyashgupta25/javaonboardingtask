package de.appsfactory.ddd.adapter.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
class UserJpaEntity {

    @Id
    @GeneratedValue
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

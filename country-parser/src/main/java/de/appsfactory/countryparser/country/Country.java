package de.appsfactory.countryparser.country;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    private Long id;

    @NonNull
    private String nameEN;

    @NonNull
    private String nameDE;

    @NonNull
    private String countryCode;
    private double longitude;
    private double latitude;

    private String altNameDE;
    private String numericISO3166;
    private String alpha2ISO3166;
    private String alpha3ISO3166;
    private String fips10;
    private String stagnag1059;
    private String domain;
    private String ioc;
    private String licensePlate;
    private String unLocode;
    private String active;

}

package de.appsfactory.countryparser.util;

import de.appsfactory.countryparser.country.Country;

public class DummyObjects {

    public static Country getCountry() {
        Country obj = Country.builder().id(1L)
                .nameEN("Germany")
                .nameDE("Deutschland")
                .countryCode("49")
                .latitude(52.520008)
                .longitude(13.404954)
                .altNameDE("")
                .numericISO3166("276")
                .alpha2ISO3166("DE")
                .alpha3ISO3166("DEU")
                .fips10("GM")
                .stagnag1059("DEU")
                .domain("de")
                .ioc("GER")
                .licensePlate("D")
                .unLocode("DE")
                .active("A")
                .build();
        return obj;
    }

}

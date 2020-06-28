package de.appsfactory.ddd.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class IsoUtil {
    private static final Set<String> ISO_COUNTRIES = new HashSet<String>
            (Arrays.asList(Locale.getISOCountries()));

    public static boolean isValidISOCountry(String s) {
        return ISO_COUNTRIES.contains(s);
    }
}

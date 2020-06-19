package de.appsfactory.countryparser.parser;

import de.appsfactory.countryparser.country.Country;
import de.appsfactory.countryparser.error.exception.ParserIOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component("csvParser")
@Slf4j
public class CSVParser implements Parser {

    private static final String[] HEADERS = {"ID", "NAME_EN", "NAME_DE", "ALT_NAME_DE", "ISO_3166_1_NUMERIC",
            "ISO_3166_1_ALPHA_2", "ISO_3166_1_ALPHA_3", "FIPS10", "STANAG_1059", "DOMAIN",
            "COUNTRY_CODE", "IOC", "LICENSE_PLATE", "UN_LOCODE", "LONGITUDE",
            "LATITUDE", "ACTIVE"};
    private static final String FILE_NAME = "/countries.csv";

    @Override
    public List<Country> getAllCountries() {
        List<Country> countryList = new ArrayList<>();
        try {
            URL resource = getClass().getResource(FILE_NAME);
            Reader in = new FileReader(new File(resource.toURI()));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withIgnoreSurroundingSpaces().withHeader(HEADERS).withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                Country country = Country.builder().id(NumberUtils.toLong(record.get(HEADERS[0])))
                        .nameEN(record.get(HEADERS[1]))
                        .nameDE(record.get(HEADERS[2]))
                        .countryCode(record.get(HEADERS[10]))
                        .latitude(NumberUtils.toDouble(record.get(HEADERS[15])))
                        .longitude(NumberUtils.toDouble(record.get(HEADERS[14])))
                        .altNameDE(record.get(HEADERS[3]))
                        .numericISO3166(record.get(HEADERS[4]))
                        .alpha2ISO3166(record.get(HEADERS[5]))
                        .alpha3ISO3166(record.get(HEADERS[6]))
                        .fips10(record.get(HEADERS[7]))
                        .stagnag1059(record.get(HEADERS[8]))
                        .domain(record.get(HEADERS[9]))
                        .ioc(record.get(HEADERS[11]))
                        .licensePlate(record.get(HEADERS[12]))
                        .unLocode(record.get(HEADERS[13]))
                        .active(record.get(HEADERS[16]))
                        .build();
                countryList.add(country);
            }
        } catch (URISyntaxException | IOException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new ParserIOException("Error parsing "+ FILE_NAME, e);
        }
        return countryList;
    }
}

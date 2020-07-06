package de.appsfactory.ddd.error.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static de.appsfactory.ddd.error.exception.ErrorMessageConvert.toMap;

public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException(Class clazz, String... searchParamsMap) {
        super(InvalidParameterException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) +
                " with invalid parameters " +
                searchParams;
    }
}

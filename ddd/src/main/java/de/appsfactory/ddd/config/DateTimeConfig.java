package de.appsfactory.ddd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import java.time.format.DateTimeFormatter;

@Configuration
class DateTimeConfig {

    private static final String GLOBAL_DATE_FORMAT = "dd.MM.yyyy";
    private static final String GLOBAL_DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";

    @Bean
    public FormattingConversionService conversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());

        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateFormatter(DateTimeFormatter.ofPattern(GLOBAL_DATE_FORMAT));
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern(GLOBAL_DATE_TIME_FORMAT));
        registrar.registerFormatters(conversionService);

        return conversionService;
    }
}
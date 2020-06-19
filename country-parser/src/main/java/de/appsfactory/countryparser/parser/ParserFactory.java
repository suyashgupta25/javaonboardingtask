package de.appsfactory.countryparser.parser;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public class ParserFactory extends AbstractFactoryBean<Parser>  {

    @Getter
    @Setter
    private Parser parser;

    @Override
    public Class<?> getObjectType() {
        return Parser.class;
    }

    @Override
    protected Parser createInstance() {
        return parser;
    }
}

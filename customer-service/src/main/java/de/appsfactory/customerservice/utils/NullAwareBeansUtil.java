package de.appsfactory.customerservice.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

@Component
public class NullAwareBeansUtil<T> {
    public T copyNonNullProperties(T target, T in) {
        if (in == null || target == null || target.getClass() != in.getClass()) return null;

        final BeanWrapper src = new BeanWrapperImpl(in);
        final BeanWrapper trg = new BeanWrapperImpl(target);

        for (final Field property : target.getClass().getDeclaredFields()) {
            Object providedObject = src.getPropertyValue(property.getName());
            if (providedObject != null && !(providedObject instanceof Collection<?>)) {
                trg.setPropertyValue(
                        property.getName(),
                        providedObject);
            }
        }
        return target;
    }
}

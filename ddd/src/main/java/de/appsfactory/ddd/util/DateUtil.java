package de.appsfactory.ddd.util;

import lombok.NonNull;

import java.util.Date;

public class DateUtil {

    public static boolean isInThePast(@NonNull Date value){
        return value.before(new Date());
    }
}

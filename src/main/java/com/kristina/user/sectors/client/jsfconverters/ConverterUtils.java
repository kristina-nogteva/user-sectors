package com.kristina.user.sectors.client.jsfconverters;

public class ConverterUtils {

    public static String extractValue(String fieldName, String value) {

        if (value.indexOf("@") > 0) {
            int from = value.indexOf(fieldName + "=") + (fieldName.length() + 1);
            int to = value.indexOf(",", from);
            if (to < 0) {
                to = value.indexOf("]", from);
            }

            return value.substring(from, to);
        } else if (value.indexOf(" - ") > 0 ){
            return value.split(" - ")[0];
        }

        return value;
    }
}

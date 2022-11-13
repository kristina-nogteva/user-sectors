package com.kristina.user.sectors.client.jsfconverters;

public class ConverterUtils {

    public static String extractValue(String fieldName, String value) {


        if (!value.contains(fieldName)){
            throw new IllegalArgumentException("Field with name " + fieldName + " cannot be extracted!");
        }

        String returnValue = value;
        if (value.indexOf("@") > 0) {
            int from = value.indexOf(fieldName + "=") + (fieldName.length() + 1);
            int to = value.indexOf("]", from);
            returnValue = value.substring(from, to);
        }

        if ("null".equals(returnValue)) return null;
        return returnValue;
    }
}

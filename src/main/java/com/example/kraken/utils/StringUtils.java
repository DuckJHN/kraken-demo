package com.example.kraken.utils;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StringUtils {
    public static String convertDataToInputParameters(Map<String, Object> data) {
        StringBuilder inputParameters = new StringBuilder();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            inputParameters.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }

        inputParameters.deleteCharAt(inputParameters.length() - 1);
        return inputParameters.toString();
    }
}

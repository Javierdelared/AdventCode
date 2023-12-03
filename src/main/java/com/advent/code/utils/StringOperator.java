package com.advent.code.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringOperator {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringOperator.class);

    private final static Pattern pattern1 = Pattern.compile("[0-9]");
    private final static Pattern pattern1Greedy = Pattern.compile(".*([0-9])");
    private final static Pattern pattern2 = Pattern.compile("[0-9]|one|two|three|four|five|six|seven|eight|nine");
    private final static Pattern pattern2Greedy = Pattern.compile(".*([0-9]|one|two|three|four|five|six|seven|eight|nine)");
    public static Map<String, String> NUMBERS = Map.of("one", "1", "two", "2",
            "three", "3", "four", "4", "five", "5", "six", "6",
            "seven", "7", "eight", "8", "nine", "9");
    public static int getCalibrationValue(String line) {
        StringBuilder stringDigitsBuilder = new StringBuilder();
        Matcher matcherFirst = pattern1.matcher(line);
        if (matcherFirst.find()) {
            stringDigitsBuilder.append(matcherFirst.group());
        }
        Matcher matcherLast = pattern1Greedy.matcher(line);
        if (matcherLast.find()) {
            stringDigitsBuilder.append(matcherLast.group(1));
        }
        String stringDigits = stringDigitsBuilder.toString();
        LOGGER.debug("Calibration value: {}", stringDigits);
        return Integer.parseInt(stringDigits);
    }

    public static int getCalibrationValue2(String line) {
        StringBuilder stringDigitsBuilder = new StringBuilder();
        Matcher matcherFirst = pattern2.matcher(line);
        if (matcherFirst.find()) {
            stringDigitsBuilder.append(matcherFirst.group());
        }
        Matcher matcherLast = pattern2Greedy.matcher(line);
        if (matcherLast.find()) {
            stringDigitsBuilder.append(matcherLast.group(1));
        }
        String stringDigits = stringDigitsBuilder.toString();
        for (Map.Entry<String, String> n : NUMBERS.entrySet()) {
            stringDigits = stringDigits.replaceAll(n.getKey(), n.getValue());
        }
        LOGGER.debug("Calibration value: {}", stringDigits);
        return Integer.parseInt(stringDigits);
    }
}
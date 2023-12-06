package com.advent.code.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ParseUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParseUtils.class);

    private final static Pattern patternDigits = Pattern.compile("[0-9]");
    private final static Pattern patternDigitsGreedy = Pattern.compile(".*([0-9])");
    private final static Pattern patternDigitsExtended = Pattern.compile("[0-9]|one|two|three|four|five|six|seven|eight|nine");
    private final static Pattern patternDigitsExtendedGreedy = Pattern.compile(".*([0-9]|one|two|three|four|five|six|seven|eight|nine)");
    public static Map<String, String> NUMBERS = Map.of("one", "1", "two", "2",
            "three", "3", "four", "4", "five", "5", "six", "6",
            "seven", "7", "eight", "8", "nine", "9");
    public static int parseCalibrationValue(String line) {
        StringBuilder stringDigitsBuilder = new StringBuilder();
        Matcher matcherFirst = patternDigits.matcher(line);
        if (matcherFirst.find()) {
            stringDigitsBuilder.append(matcherFirst.group());
        }
        Matcher matcherLast = patternDigitsGreedy.matcher(line);
        if (matcherLast.find()) {
            stringDigitsBuilder.append(matcherLast.group(1));
        }
        String stringDigits = stringDigitsBuilder.toString();
        LOGGER.debug("Calibration value: {}", stringDigits);
        return Integer.parseInt(stringDigits);
    }

    public static int parseCalibrationValueExtended(String line) {
        StringBuilder stringDigitsBuilder = new StringBuilder();
        Matcher matcherFirst = patternDigitsExtended.matcher(line);
        if (matcherFirst.find()) {
            stringDigitsBuilder.append(matcherFirst.group());
        }
        Matcher matcherLast = patternDigitsExtendedGreedy.matcher(line);
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

    public static List<Long> parseLongNumbers(String line) {
        return Stream.of(line.split(" ")).filter(s -> s.matches("[0-9]+"))
                .map(Long::parseLong).toList();
    }
}
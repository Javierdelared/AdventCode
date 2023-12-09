package com.advent.code.utils;

import java.util.List;
import java.util.stream.Stream;

public class ParseUtils {
    public static List<Long> parseLongNumbers(String line) {
        return Stream.of(line.split(" ")).filter(s -> s.matches("^-?[0-9]+$"))
                .map(Long::parseLong).toList();
    }
}
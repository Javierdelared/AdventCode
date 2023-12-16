package com.advent.code.utils;

import com.advent.code.models.Coordinates;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParseUtils {
    public static List<Long> parseLongNumbers(String line) {
        return Stream.of(line.split(" ")).filter(s -> s.matches("^-?[0-9]+$"))
                .map(Long::parseLong).toList();
    }

    public static List<Boolean> parseLineToBooleanList(String line, char c1) {
        return line.chars().mapToObj(c -> (char) c == c1).toList();
    }

    public static List<Coordinates> parseLineToCoordinates(String line, int y, char c1) {
        return IntStream.range(0, line.length()).filter(x -> line.charAt(x) == c1)
                .mapToObj(x -> new Coordinates(x, y)).toList();
    }
}
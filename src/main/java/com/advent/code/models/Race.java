package com.advent.code.models;

import com.advent.code.utils.ParseUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Race {

    private final long time;
    private final long distance;

    public Race(long time, long distance) {
        this.time = time;
        this.distance = distance;
    }

    public long calculatePossibleTimes() {
        long i = 1;
        while (i * (time - i) <= distance) {
            i++;
        }
        return time - (2L*i) + 1;
    }

    public static List<Race> parseRaces(List<String> lines) {
        List<Race> races = new ArrayList<>();
        List<Long> times = ParseUtils.parseLongNumbers(lines.get(0));
        List<Long> distances = ParseUtils.parseLongNumbers(lines.get(1));
        for (int i = 0; i < times.size(); i++) {
            races.add(new Race(times.get(i), distances.get(i)));
        }
        return races;
    }

    public static Race parseRace(List<String> lines) {
        return new Race(getLongNumber(lines.get(0)), getLongNumber(lines.get(1)));
    }

    public static Long getLongNumber(String line) {
        return Long.parseLong(Stream.of(line.split(" ")).filter(s -> s.matches("[0-9]+"))
                .collect(Collectors.joining("")));
    }
}

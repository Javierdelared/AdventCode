package com.advent.code.dto;

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
}

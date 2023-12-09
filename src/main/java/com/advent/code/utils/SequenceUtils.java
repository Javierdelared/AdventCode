package com.advent.code.utils;

import java.util.ArrayList;
import java.util.List;

public class SequenceUtils {

    public static long calculateNext(List<Long> sequence) {
        List<Long> lastElement = new ArrayList<>();
        List<Long> currentSequence = sequence;
        while (!currentSequence.stream().allMatch(x -> x == 0L)) {
            lastElement.add(currentSequence.get(currentSequence.size() - 1));
            List<Long> newSequence = new ArrayList<>();
            for (int i = 1; i < currentSequence.size() ; i++) {
                newSequence.add(currentSequence.get(i) - currentSequence.get(i-1));
            }
            currentSequence = newSequence;
        }
        return lastElement.stream().mapToLong(Long::longValue).sum();
    }

    public static long calculatePrevious(List<Long> sequence) {
        List<Long> firstElement = new ArrayList<>();
        List<Long> currentSequence = sequence;
        while (!currentSequence.stream().allMatch(x -> x == 0L)) {
            firstElement.add(currentSequence.get(0));
            List<Long> newSequence = new ArrayList<>();
            for (int i = 1; i < currentSequence.size() ; i++) {
                newSequence.add(currentSequence.get(i) - currentSequence.get(i-1));
            }
            currentSequence = newSequence;
        }
        long previousElement = 0;
        for (int i = 0; i < firstElement.size(); i++) {
            if (i % 2 == 0) {
                previousElement += firstElement.get(i);
            } else {
                previousElement -= firstElement.get(i);
            }
        }
        return previousElement;
    }
}

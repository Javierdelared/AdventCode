package com.advent.code.models;

import java.util.*;
import java.util.stream.IntStream;

public class SpringRow {

    private static final Map<SpringRow, Long> cacheCombinations = new HashMap<>();
    private final String row;
    private final int[] groups;

    public SpringRow(String row, int[] groups) {
        this.row = row;
        this.groups = groups;
    }

    public SpringRow(String line) {
        String[] row = line.split(" ");
        this.row = row[0];
        this.groups = Arrays.stream(row[1].split(",")).mapToInt(Integer::parseInt).toArray();
    }

    public long countUnfoldedCombinations(int copies) {
        return countCombinations(new SpringRow((row + "?").repeat(copies - 1) + row,
                IntStream.range(0, copies).flatMap(i -> Arrays.stream(groups)).toArray()));
    }

    public static long countCombinations(SpringRow springRow) {
        String row = springRow.row;
        int[] groups = springRow.groups;
        if (groups.length == 0) {
            return row.contains("#") ? 0 : 1;
        }
        int firstGroup = groups[0];
        int firstFit = row.replace("?", "#").indexOf("#".repeat(firstGroup));
        int lastFit = row.length() - Arrays.stream(groups).reduce(0, Integer::sum) - groups.length + 1;
        if (firstFit == -1 || lastFit < firstFit) {
            return 0;
        }
        return IntStream.rangeClosed(firstFit, lastFit)
                .filter(i -> isValidMatch(row, i, firstGroup))
                .mapToLong(i -> count(row, groups, firstGroup, i)).sum();
    }

    private static long count(String row, int[] groups, int firstGroup, int i) {
        row = row.length() > i + firstGroup ? row.substring(i + firstGroup + 1) : "";
        SpringRow newSpringRow = new SpringRow(row, Arrays.copyOfRange(groups, 1, groups.length));
        if (cacheCombinations.get(newSpringRow) == null) {
            cacheCombinations.put(newSpringRow, countCombinations(newSpringRow));
        }
        return cacheCombinations.get(newSpringRow);
    }

    private static boolean isValidMatch(String row, int index, int firstGroup) {
        return IntStream.range(0, index).noneMatch(i -> row.charAt(i) == '#') &&
                IntStream.range(index, index + firstGroup).noneMatch(i -> row.charAt(i) == '.') &&
                (row.length() <= index + firstGroup || row.charAt(index + firstGroup) != '#');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpringRow springRow = (SpringRow) o;
        return Objects.equals(row, springRow.row) && Arrays.equals(groups, springRow.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, Arrays.hashCode(groups));
    }
}

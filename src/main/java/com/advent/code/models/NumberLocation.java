package com.advent.code.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NumberLocation {

    private final static Pattern patternNumbers = Pattern.compile("[0-9]+");

    private final int numberValue;

    private final List<Position> positions = new ArrayList<>();

    public NumberLocation(int numberValue, int xPosition, int yPosition, int length) {
        this.numberValue = numberValue;
        for (int i = 0; i < length; i++) {
            positions.add(new Position(xPosition + i, yPosition));
        }
    }

    public int getNumberValue() {
        return numberValue;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public boolean hasAdjacent(List<Position> symbolPositions) {
        Set<Position> adjacentPositions = positions.stream().
                flatMap(p -> p.getAdjacentPositions().stream())
                .collect(Collectors.toSet());
        adjacentPositions.retainAll(symbolPositions);
        return !adjacentPositions.isEmpty();
    }

    public static List<NumberLocation> parseNumbersLocations(int lineNumber, String line) {
        Matcher matcher = patternNumbers.matcher(line);
        List<NumberLocation> numberLocationList = new ArrayList<>();
        while (matcher.find()) {
            NumberLocation numberLocation = new NumberLocation(Integer.parseInt(matcher.group()),
                    matcher.start(), lineNumber, matcher.group().length());
            numberLocationList.add(numberLocation);
        }
        return numberLocationList;
    }
}

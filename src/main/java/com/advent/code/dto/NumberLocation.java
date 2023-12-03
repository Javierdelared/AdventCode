package com.advent.code.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NumberLocation {

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

    public boolean hasAdjacent(List<Position> symbolPositions) {
        Set<Position> adjacentPositions = positions.stream().
                flatMap(p -> p.getAdjacentPositions().stream())
                .collect(Collectors.toSet());
        adjacentPositions.retainAll(symbolPositions);
        return !adjacentPositions.isEmpty();
    }
}

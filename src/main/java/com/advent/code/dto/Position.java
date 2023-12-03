package com.advent.code.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Position {

    private final Integer x;
    private final Integer y;

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Set<Position> getAdjacentPositions() {
        Set<Position> positions = new HashSet<>();
        for (int i = -1; i <=1; i++) {
            for (int j = -1; j<=1; j++) {
                positions.add(new Position(x+i, y+j));
            }
        }
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(x, position.x) && Objects.equals(y, position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

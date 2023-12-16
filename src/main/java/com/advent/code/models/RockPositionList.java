package com.advent.code.models;

import java.util.List;
import java.util.OptionalInt;

public record RockPositionList(List<Coordinates> coordinatesList) {

    public boolean contains(Coordinates coordinates) {
        return this.coordinatesList().contains(coordinates);
    }

    public OptionalInt getFixedRock(Direction direction, int x, int y) {
        return switch (direction) {
            case NORTH -> coordinatesList.stream().filter(c -> c.x() == x && c.y() < y).mapToInt(Coordinates::y).max();
            case WEST -> coordinatesList.stream().filter(c -> c.x() < x && c.y() == y).mapToInt(Coordinates::x).max();
            case SOUTH -> coordinatesList.stream().filter(c -> c.x() == x && c.y() > y).mapToInt(Coordinates::y).min();
            case EAST -> coordinatesList.stream().filter(c -> c.x() > x && c.y() == y).mapToInt(Coordinates::x).min();
        };
    }

}

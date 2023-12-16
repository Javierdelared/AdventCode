package com.advent.code.models;

import java.util.List;
import java.util.stream.IntStream;

public record RockPositionList(List<Coordinates> coordinatesList) {

    public boolean contains(Coordinates coordinates) {
        return coordinatesList.contains(coordinates);
    }

    public int calculateLoad(Direction direction, Coordinates maxCoordinates) {
        return IntStream.range(0, maxCoordinates.x()).map(x ->
                IntStream.range(0, maxCoordinates.y())
                        .mapToObj(y -> new Coordinates(x, y))
                        .filter(this::contains)
                        .mapToInt(c -> calculateRockLoad(c, direction, maxCoordinates)).sum()).sum();
    }

    private int calculateRockLoad(Coordinates coordinates, Direction d, Coordinates maxCoordinates) {
        return switch (d) {
            case NORTH -> maxCoordinates.y() - coordinates.y();
            case WEST -> maxCoordinates.x() - coordinates.x();
            case SOUTH -> coordinates.y() + 1;
            case EAST -> coordinates.x() + 1;
        };
    }
}

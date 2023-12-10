package com.advent.code.models;

import static com.advent.code.models.Direction.*;

public record Coordinates(int x, int y) {
    public Coordinates move(Direction d) {
        return switch (d) {
            case NORTH -> new Coordinates(x, y - 1);
            case EAST -> new Coordinates(x + 1, y);
            case SOUTH -> new Coordinates(x, y + 1);
            case WEST -> new Coordinates(x - 1, y);
        };
    }

    public int calculateDistance(Coordinates d) {
        return Math.abs(x - d.x()) + Math.abs(y - d.y());
    }

    public Direction approachEastWest(Coordinates c2) {
        return x - c2.x() > 0 ? WEST : EAST;
    }

    public Direction approachNorthSouth(Coordinates c2) {
        return y - c2.y() > 0 ? NORTH : SOUTH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }
}

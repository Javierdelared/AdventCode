package com.advent.code.models;

import java.util.List;

import static com.advent.code.models.Direction.*;

public record Coordinates(int x, int y) {

    public static final Coordinates ORIGIN = new Coordinates(0,0);

    public Coordinates move(Direction d) {
        return move(d, 1);
    }

    public Coordinates move(Direction d, int speed) {
        return switch (d) {
            case NORTH -> new Coordinates(x, y - speed);
            case EAST -> new Coordinates(x + speed, y);
            case SOUTH -> new Coordinates(x, y + speed);
            case WEST -> new Coordinates(x - speed, y);
        };
    }

    public int calculateDistance(Coordinates d) {
        return Math.abs(x - d.x) + Math.abs(y - d.y);
    }

    public Direction approachEastWest(Coordinates c2) {
        return x - c2.x > 0 ? WEST : EAST;
    }

    public Direction approachNorthSouth(Coordinates c2) {
        return y - c2.y > 0 ? NORTH : SOUTH;
    }

    public boolean isInRange(Coordinates c1, Coordinates c2) {
        return x >= c1.y && x < c2.x && y >= c1.y && y < c2.y;
    }

    public static Coordinates getMaxCoordinates(List<String> lines) {
        return new Coordinates(lines.get(0).length(), lines.size());
    }

    public static Coordinates getBottomRightCoordinates(List<String> lines) {
        return new Coordinates(lines.get(0).length() - 1, lines.size() - 1);
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

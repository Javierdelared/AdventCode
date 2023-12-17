package com.advent.code.models;

public record Vector(Coordinates c, Direction d, int speed) {

    public static Vector build(int x, int y, Direction d) {
        return new Vector(new Coordinates(x, y), d, 1);
    }

    public Vector move() {
        return new Vector(c.move(d), d, speed);
    }

    public Vector move(Direction d1) {
        return new Vector(c.move(d1), d1, speed);
    }

    public Vector move(Direction d1, int speed) {
        return new Vector(c.move(d1, speed), d1, speed);
    }
}

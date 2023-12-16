package com.advent.code.models;

public record Vector(Coordinates c, Direction d) {

    public static Vector build(int x, int y, Direction d) {
        return new Vector(new Coordinates(x, y), d);
    }

    public Vector move() {
        return new Vector(c.move(d), d);
    }
    public Vector move(Direction d1) {
        return new Vector(c.move(d1), d1);
    }
}

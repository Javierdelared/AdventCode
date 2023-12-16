package com.advent.code.models;

import java.util.List;

import static com.advent.code.models.Direction.*;

public enum Mirror {
    FORWARD('/'), BACKWARD('\\'), HORIZONTAL('-'), VERTICAL('|');

    public final char symbol;

    Mirror(char symbol) {
        this.symbol = symbol;
    }

    public List<Direction> reflect(Direction d) {
        return switch (this) {
            case FORWARD -> mirrorForward(d);
            case BACKWARD -> mirrorBackward(d);
            case HORIZONTAL -> splitHorizontal(d);
            case VERTICAL -> splitVertical(d);
        };
    }

    public static List<Direction> mirrorForward(Direction d) {
        return switch (d) {
            case NORTH -> List.of(EAST);
            case WEST -> List.of(SOUTH);
            case SOUTH -> List.of(WEST);
            case EAST -> List.of(NORTH);
        };
    }

    public static List<Direction> mirrorBackward(Direction d) {
        return switch (d) {
            case NORTH -> List.of(WEST);
            case WEST -> List.of(NORTH);
            case SOUTH -> List.of(EAST);
            case EAST -> List.of(SOUTH);
        };
    }

    private List<Direction> splitHorizontal(Direction d) {
        return switch (d) {
            case NORTH, SOUTH -> List.of(EAST, WEST);
            case WEST, EAST -> List.of(d);
        };
    }

    private List<Direction> splitVertical(Direction d) {
        return switch (d) {
            case NORTH, SOUTH -> List.of(d);
            case WEST, EAST -> List.of(NORTH, SOUTH);
        };
    }
}

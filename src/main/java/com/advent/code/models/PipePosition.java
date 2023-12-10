package com.advent.code.models;

import com.advent.code.exception.ServiceException;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static com.advent.code.models.Direction.*;

public class PipePosition {
    private final Coordinates coordinates;
    private char symbol;
    private Set<Direction> directions;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Set<Direction> getDirections() {
        if (directions == null) {
            directions = calculateDirections(symbol);
        }
        return directions;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setNewDirections(Set<Direction> directions) {
        this.directions = directions;
        this.symbol = adaptPipeSymbolToDirections(directions);
    }

    public PipePosition(int x, int y, char symbol) {
        coordinates = new Coordinates(x, y);
        this.symbol = symbol;
    }

    private Set<Direction> calculateDirections(char symbol) {
        return switch (symbol) {
            case 'S' -> Set.of(Direction.values());
            case '|' -> Set.of(NORTH, SOUTH);
            case '-' -> Set.of(EAST, WEST);
            case 'L' -> Set.of(NORTH, EAST);
            case 'J' -> Set.of(NORTH, WEST);
            case '7' -> Set.of(SOUTH, WEST);
            case 'F' -> Set.of(SOUTH, EAST);
            case '.' -> Set.of();
            default -> throw new ServiceException(String.format("Symbol not recognised: [%s]", symbol));
        };
    }

    private char adaptPipeSymbolToDirections(Set<Direction> directions) {
        List<Character> pipeChars = List.of('|', '-', 'L', 'J', '7', 'F');
        for(char c : pipeChars) {
            if (directions.containsAll(calculateDirections(c))) {
                return c;
            }
        }
        throw  new ServiceException("No pipe symbol matches specified directions");
    }

    public boolean isPipeConnected(Direction direction) {
        if (directions == null) {
            directions = calculateDirections(symbol);
        }
        return directions.contains(direction);
    }

    public static List<PipePosition> parseMazePositions(String line, int y) {
        return IntStream.range(0, line.length()).mapToObj(i -> new PipePosition(i, y, line.charAt(i))).toList();
    }
}

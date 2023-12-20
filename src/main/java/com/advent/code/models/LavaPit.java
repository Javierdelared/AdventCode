package com.advent.code.models;

import com.advent.code.exception.ServiceException;

import java.util.*;

import static com.advent.code.models.Direction.*;

public class LavaPit {

    private final LinkedList<Vector> borderVectors = new LinkedList<>();

    public LavaPit() {
        borderVectors.add(new Vector(Coordinates.ORIGIN, null, 0));
    }

    public void  parseBorders(List<String> lines) {
        lines.forEach(this::parseBorder);
        borderVectors.removeFirst();
    }
    public void parseBordersColours(List<String> lines) {
        lines.forEach(this::parseBorderColour);
        borderVectors.removeFirst();
    }
    private void parseBorder(String line) {
        String[] lineArr = line.split(" ");
        Direction d = getDirection(lineArr[0].charAt(0));
        int speed = Integer.parseInt(lineArr[1]);
        borderVectors.addLast(borderVectors.getLast().move(d, speed));
    }

    private void parseBorderColour(String line) {
        String[] lineArr = line.split(" ");
        Direction d = getDirectionFromColour(lineArr[2].charAt(7));
        int speed = getHexadecimal(lineArr[2].substring(2, 7));
        borderVectors.add(borderVectors.getLast().move(d, speed));
    }

    public long calculateArea() {
        Vector borderBefore = borderVectors.getLast();
        Vector borderVector = borderVectors.getFirst();
        Vector borderAfter = borderVectors.get(1);
        long totalArea = calculateArea(borderVector, borderBefore, borderAfter);
        for (int j = 2; j <= borderVectors.size(); j++) {
            borderBefore = borderVector;
            borderVector = borderAfter;
            borderAfter = borderVectors.get(j % borderVectors.size());
            totalArea += calculateArea(borderVector, borderBefore, borderAfter);
        }
        return totalArea;
    }

    private long calculateArea(Vector v1, Vector v2, Vector v3) {
        long speed = v1.speed();
        switch (v1.d()) {
            case NORTH -> {
                if (v2.d().equals(WEST) && v3.d().equals(EAST)) {
                    speed++;
                }
                if (v2.d().equals(EAST) && v3.d().equals(WEST)) {
                    speed--;
                }
                return -1 * speed * v1.c().x();
            }
            case SOUTH -> {
                if (v2.d().equals(EAST) && v3.d().equals(WEST)){
                    speed++;
                }
                if (v2.d().equals(WEST) && v3.d().equals(EAST)){
                    speed--;
                }
                return speed * (v1.c().x() + 1);
            }
            case WEST, EAST -> {
                return 0;
            }
        }
        return 0;
    }

    private Direction getDirection(char c) {
        return switch (c) {
            case 'U' -> Direction.NORTH;
            case 'L' -> Direction.WEST;
            case 'D' -> Direction.SOUTH;
            case 'R' -> Direction.EAST;
            default -> throw new ServiceException("Invalid direction: " + c);
        };
    }

    private int getHexadecimal(String hex) {
        int val = 0;
        for (int i = 0; i < hex.length(); i++) {
            val = 16*val + "0123456789abcdef".indexOf(hex.charAt(i));
        }
        return val;
    }

    private Direction getDirectionFromColour(char c) {
        return switch (c) {
            case '0' -> EAST;
            case '1' -> SOUTH;
            case '2' -> WEST;
            case '3' -> NORTH;
            default -> throw new ServiceException("Invalid direction: " + c);
        };
    }
}

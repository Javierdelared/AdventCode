package com.advent.code.models;

import java.util.*;
import java.util.stream.IntStream;

import static com.advent.code.models.Direction.*;

public class HeatMap {

    private final Map<Coordinates, Integer> map;
    private final int minSpeed;
    private final int maxSpeed;
    private final Coordinates start;
    private final Coordinates end;
    private final Map<Coordinates, Map<Direction, Map<Integer, Integer>>> heatCache = new HashMap<>();
    private int minHeat = Integer.MAX_VALUE;

    public HeatMap(Map<Coordinates, Integer> map, int minSpeed, int maxSpeed, Coordinates start, Coordinates end) {
        this.map = map;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.start = start;
        this.end = end;

        map.keySet().forEach(c -> {
            Map<Direction, Map<Integer, Integer>> directionMap = new HashMap<>();
            Arrays.stream(values()).forEach(d -> {
                Map<Integer, Integer> speedMap = new HashMap<>();
                IntStream.range(minSpeed, maxSpeed + 1).forEach(s -> speedMap.put(s, Integer.MAX_VALUE));
                directionMap.put(d, new HashMap<>(speedMap));
            });
            heatCache.put(c, new HashMap<>(directionMap));
        });
    }

    public int calculateMinHeat() {
        moveToPossibleTiles(List.of(values()), new Vector(start, null, 0), 0);
        return minHeat;
    }

    public void move(Vector v, int heat) {
        int finalHeat = heat + IntStream.range(0, v.speed()).map(s -> map.get(v.c().move(v.d().getOpposite(), s))).sum();
        heatCache.get(v.c()).get(v.d()).entrySet().stream()
                .filter(e -> e.getKey() > v.speed() && e.getValue() > finalHeat)
                .forEach(e -> e.setValue(finalHeat));
        if (finalHeat >= heatCache.get(v.c()).get(v.d()).get(v.speed())) {
            return ;
        } else {
            heatCache.get(v.c()).get(v.d()).put(v.speed(), finalHeat);
        }
        if (finalHeat >= minHeat) {
            return ;
        }
        if (end.equals(v.c())) {
            minHeat = finalHeat;
            return ;
        }
        moveToPossibleTiles(getPerpendicular(v.d()), v, finalHeat);
    }

    private void moveToPossibleTiles(List<Direction> directions, Vector v, int heat) {
        directions.stream().flatMap(d -> IntStream.iterate(maxSpeed, s -> s >= minSpeed, s -> s - 1)
                        .mapToObj(s -> v.move(d, s))).filter(v1 -> map.containsKey(v1.c()))
                .forEach(pv -> move(pv, heat));
    }
    public List<Direction> getPerpendicular(Direction d) {
        return switch (d) {
            case NORTH, SOUTH -> List.of(EAST, WEST);
            case WEST, EAST -> List.of(SOUTH, NORTH);
        };
    }
}

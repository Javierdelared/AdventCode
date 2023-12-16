package com.advent.code.models;

import java.util.*;
import java.util.stream.IntStream;

import static com.advent.code.models.Direction.*;

public class TiltedRockPlatform {
    private final RockPositionList fixedRockCoordinates;
    private RockPositionList rollingRockCoordinates;
    private final Coordinates maxCoordinates;
    private Map<Direction, Map<Coordinates, Coordinates>> rollingRockMaxCoordinatesMap;
    private final Map<Direction, Map<RockPositionList, RockPositionList>> rollingRockCoordinatesCache = new HashMap<>();
    private boolean loopFound;
    private final Map<RockPositionList, Long> loopCycleMap = new HashMap<>();
    private int loopSize;

    public Coordinates getMaxCoordinates() {
        return maxCoordinates;
    }

    public boolean isLoopFound() {
        return loopFound;
    }

    public Map<RockPositionList, Long> getLoopCycleMap() {
        return loopCycleMap;
    }

    public long getLoopSize() {
        return loopSize;
    }

    public TiltedRockPlatform(List<Coordinates> fixedRockCoordinates, List<Coordinates> rollingRockCoordinates,
                              Coordinates maxCoordinates) {
        this.fixedRockCoordinates = new RockPositionList(fixedRockCoordinates);
        this.rollingRockCoordinates = new RockPositionList(rollingRockCoordinates);
        this.maxCoordinates = maxCoordinates;
        Arrays.stream(Direction.values()).forEach(d -> rollingRockCoordinatesCache.put(d, new HashMap<>()));
    }

    private Map<Direction, Map<Coordinates, Coordinates>> getRollingMaxCoordinatesMap() {
        if (rollingRockMaxCoordinatesMap == null) {
            rollingRockMaxCoordinatesMap = calculateRollingMaxCoordinatesMap();
        }
        return rollingRockMaxCoordinatesMap;
    }

    private Map<Direction, Map<Coordinates, Coordinates>> calculateRollingMaxCoordinatesMap() {
        Map<Direction, Map<Coordinates, Coordinates>> rollingMaxValueDirectionMap = new HashMap<>();
        Arrays.stream(values()).forEach(direction -> {
            Map<Coordinates, Coordinates> rollingMapValueMap = new HashMap<>();
            IntStream.range(0, maxCoordinates.x()).forEach(x ->
                IntStream.range(0, maxCoordinates.y()).forEach(y ->
                    rollingMapValueMap.put(new Coordinates(x, y), calculateMaxCoordinates(x, y, direction))));
            rollingMaxValueDirectionMap.put(direction, rollingMapValueMap);
        });
        return rollingMaxValueDirectionMap;
    }

    private Coordinates calculateMaxCoordinates(int x, int y, Direction direction) {
        OptionalInt fixedRock = fixedRockCoordinates.getFixedRock(direction, x, y);
        return switch (direction) {
            case NORTH -> new Coordinates(x, fixedRock.isPresent() ? fixedRock.getAsInt() + 1 : 0);
            case WEST -> new Coordinates(fixedRock.isPresent() ? fixedRock.getAsInt() + 1 : 0, y);
            case SOUTH -> new Coordinates(x,  fixedRock.isPresent() ? fixedRock.getAsInt() - 1 : maxCoordinates.y() - 1);
            case EAST -> new Coordinates(fixedRock.isPresent() ? fixedRock.getAsInt() - 1 : maxCoordinates.x() - 1, y);
        };
    }

    public void tilt(Direction direction, long cycle) {
        if (rollingRockCoordinatesCache.get(direction).get(rollingRockCoordinates) != null) {
            rollingRockCoordinates = rollingRockCoordinatesCache.get(direction).get(rollingRockCoordinates);
            if (direction == EAST) {
                updateLoopCache(cycle);
            }
            return ;
        }
        List<Coordinates> newCoordinatesList = new ArrayList<>();
        IntStream.range(0, maxCoordinates.x()).forEach(x ->
            IntStream.range(0, maxCoordinates.y())
                .mapToObj(y -> new Coordinates(x, y))
                .filter(c -> rollingRockCoordinates.contains(c))
                .forEach(c -> {
                    Coordinates newCoordinates = getRollingMaxCoordinatesMap().get(direction).get(c);
                    while (newCoordinatesList.contains(newCoordinates)) {
                        newCoordinates = newCoordinates.move(direction.getOpposite());
                    }
                    newCoordinatesList.add(newCoordinates);
                }));
        RockPositionList newRockPositionList = new RockPositionList(newCoordinatesList);
        rollingRockCoordinatesCache.get(direction).put(rollingRockCoordinates, newRockPositionList);
        rollingRockCoordinates = newRockPositionList;
    }

    private void updateLoopCache(long cycle) {
        if (loopCycleMap.get(rollingRockCoordinates) != null) {
            loopSize = (int) (cycle - loopCycleMap.get(rollingRockCoordinates));
            if (loopCycleMap.size() == loopSize) {
                loopFound = true;
            }
        } else {
            loopCycleMap.put(rollingRockCoordinates, cycle);
        }
    }

    public int calculateCurrentLoad(Direction direction) {
        return calculateLoad(direction, maxCoordinates, rollingRockCoordinates);
    }

    public static int calculateLoad(Direction direction, Coordinates maxCoordinates,
                                    RockPositionList rollingRockCoordinates) {
        return IntStream.range(0, maxCoordinates.x()).map(x ->
                    IntStream.range(0, maxCoordinates.y())
                        .mapToObj(y -> new Coordinates(x, y))
                        .filter(rollingRockCoordinates::contains)
                        .mapToInt(c -> calculateRollingRockLoad(direction, c, maxCoordinates)).sum()).sum();
    }

    private static int calculateRollingRockLoad(Direction d, Coordinates coordinates, Coordinates maxCoordinates) {
        return switch (d) {
            case NORTH -> maxCoordinates.y() - coordinates.y();
            case WEST -> maxCoordinates.x() - coordinates.x();
            case SOUTH -> coordinates.y() + 1;
            case EAST -> coordinates.x() + 1;
        };
    }
}

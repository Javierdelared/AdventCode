package com.advent.code.models;

import java.util.*;
import java.util.stream.IntStream;

import static com.advent.code.models.Direction.*;

public class TiltedRockPlatform {
    private final RockPositionList fixedRockCoordinates;
    private RockPositionList rollingRockCoordinates;
    private final Coordinates maxC;
    private Map<Direction, Map<Coordinates, Coordinates>> rollingRockMaxCoordinatesMap;
    private final Map<Direction, Map<RockPositionList, RockPositionList>> rollingRockCoordinatesCache = new HashMap<>();
    private boolean loopFound;
    private final Map<RockPositionList, Long> loopCycleMap = new HashMap<>();
    private int loopSize;

    public Coordinates getMaxC() {
        return maxC;
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
                              Coordinates maxC) {
        this.fixedRockCoordinates = new RockPositionList(fixedRockCoordinates);
        this.rollingRockCoordinates = new RockPositionList(rollingRockCoordinates);
        this.maxC = maxC;
        Arrays.stream(Direction.values()).forEach(d -> rollingRockCoordinatesCache.put(d, new HashMap<>()));
    }

    public int calculateCurrentLoad(Direction d) {
        return rollingRockCoordinates.calculateLoad(d, maxC);
    }

    public void tilt(Direction d, long cycle) {
        if (rollingRockCoordinatesCache.get(d).get(rollingRockCoordinates) != null) {
            rollingRockCoordinates = rollingRockCoordinatesCache.get(d).get(rollingRockCoordinates);
            if (d == EAST) {
                updateLoopCache(cycle);
            }
            return ;
        }
        RockPositionList newRockPositionList = calculateNewRockPositions(d);
        rollingRockCoordinatesCache.get(d).put(rollingRockCoordinates, newRockPositionList);
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

    private RockPositionList calculateNewRockPositions(Direction d) {
        List<Coordinates> newCoordinates = new ArrayList<>();
        IntStream.range(0, maxC.x()).forEach(x ->
            IntStream.range(0, maxC.y())
                .mapToObj(y -> new Coordinates(x, y))
                .filter(c -> rollingRockCoordinates.contains(c))
                .forEach(c -> {
                    Coordinates newC = getRollingMaxCoordinatesMap().get(d).get(c);
                    while (newCoordinates.contains(newC)) {
                        newC = newC.move(d.getOpposite());
                    }
                    newCoordinates.add(newC);
                }));
        return new RockPositionList(newCoordinates);
    }

    private Map<Direction, Map<Coordinates, Coordinates>> getRollingMaxCoordinatesMap() {
        if (rollingRockMaxCoordinatesMap == null) {
            rollingRockMaxCoordinatesMap = calculateRollingMaxCoordinatesMap();
        }
        return rollingRockMaxCoordinatesMap;
    }

    private Map<Direction, Map<Coordinates, Coordinates>> calculateRollingMaxCoordinatesMap() {
        Map<Direction, Map<Coordinates, Coordinates>> rollingMaxValueDirectionMap = new HashMap<>();
        Arrays.stream(values()).forEach(d -> {
            Map<Coordinates, Coordinates> rollingMapValueMap = new HashMap<>();
            IntStream.range(0, maxC.x()).forEach(x ->
                    IntStream.range(0, maxC.y()).mapToObj(y -> new Coordinates(x, y)).forEach(c ->
                            rollingMapValueMap.put(c, calculateMaxCoordinates(c, maxC, d))));
            rollingMaxValueDirectionMap.put(d, rollingMapValueMap);
        });
        return rollingMaxValueDirectionMap;
    }

    private Coordinates calculateMaxCoordinates(Coordinates c1, Coordinates maxC, Direction d) {
        return switch (d) {
            case NORTH -> {
                OptionalInt maxRock = fixedRockCoordinates.coordinatesList().stream()
                        .filter(c -> c.x() == c1.x() && c.y() < c1.y()).mapToInt(Coordinates::y).max();
                yield new Coordinates(c1.x(), maxRock.isPresent() ? maxRock.getAsInt() + 1 : 0);
            }
            case WEST -> {
                OptionalInt maxRock = fixedRockCoordinates.coordinatesList().stream()
                        .filter(c -> c.x() < c1.x() && c.y() == c1.y()).mapToInt(Coordinates::x).max();
                yield new Coordinates(maxRock.isPresent() ? maxRock.getAsInt() + 1 : 0, c1.y());
            }
            case SOUTH -> {
                OptionalInt maxRock = fixedRockCoordinates.coordinatesList().stream()
                        .filter(c -> c.x() == c1.x() && c.y() > c1.y()).mapToInt(Coordinates::y).min();
                yield new Coordinates(c1.x(),  maxRock.isPresent() ? maxRock.getAsInt() - 1 : maxC.y() - 1);
            }
            case EAST -> {
                OptionalInt maxRock = fixedRockCoordinates.coordinatesList().stream()
                        .filter(c -> c.x() > c1.x() && c.y() == c1.y()).mapToInt(Coordinates::x).min();
                yield new Coordinates(maxRock.isPresent() ? maxRock.getAsInt() - 1 : maxC.x() - 1, c1.y());
            }
        };
    }
}

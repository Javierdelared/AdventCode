package com.advent.code.puzzles;

import com.advent.code.models.*;
import com.advent.code.utils.ParseUtils;

import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class PuzzleSolver3 extends PuzzleSolver {

    public PuzzleSolver3(String basePath) {
        super(basePath);
    }

    public long puzzle111() {
        long distance = puzzle112(2);
        LOGGER.info("Result puzzle 21: {}", distance);
        return distance;
    }

    public long puzzle112(int spaceMultiplier) {
        List<String> lines = lineReader.readLines("advent_file_11.txt");
        List<List<Boolean>> imageGalaxyPositions = lines.stream()
                .map(line -> ParseUtils.parseLineToBooleanList(line, '#')).toList();
        GalaxyMap galaxyMap = new GalaxyMap(imageGalaxyPositions, spaceMultiplier);
        List<Coordinates> galaxies = galaxyMap.getExpandedGalaxyPositions();
        long distance = 0;
        for (int i = 0; i < galaxies.size(); i ++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                distance += galaxies.get(i).calculateDistance(galaxies.get(j));
            }
        }
        LOGGER.info("Result puzzle 22: {}", distance);
        return distance;
    }

    public long puzzle121() {
        List<String> lines = lineReader.readLines("advent_file_12.txt");
        long combinations = lines.stream().map(SpringRow::new).mapToLong(SpringRow::countCombinations).sum();
        LOGGER.info("Result puzzle 23: {}", combinations);
        return combinations;
    }
    public long puzzle122() {
        List<String> lines = lineReader.readLines("advent_file_12.txt");
        long combinations = lines.stream().map(SpringRow::new)
                .mapToLong(r -> r.countUnfoldedCombinations(5)).sum();
        LOGGER.info("Result puzzle 24: {}", combinations);
        return combinations;
    }

    public int puzzle131() {
        List<String> lines = lineReader.readLines("advent_file_13.txt");
        int result = 0;
        List<List<Boolean>> currentMirrorPattern = new ArrayList<>();
        for (int i = 0; i <= lines.size(); i++) {
            if (lines.size() == i || "".equals(lines.get(i))) {
                MirrorPattern mirrorPattern = new MirrorPattern(currentMirrorPattern);
                result += mirrorPattern.calculateMirrorPositions();
                currentMirrorPattern = new ArrayList<>();
            } else {
                currentMirrorPattern.add(ParseUtils.parseLineToBooleanList(lines.get(i), '#'));
            }
        }
        LOGGER.info("Result puzzle 25: {}", result);
        return result;
    }

    public int puzzle132() {
        List<String> lines = lineReader.readLines("advent_file_13.txt");
        int result = 0;
        List<List<Boolean>> currentMirrorPattern = new ArrayList<>();
        for (int i = 0; i <= lines.size(); i++) {
            if (lines.size() == i || "".equals(lines.get(i))) {
                MirrorPattern mirrorPattern = new MirrorPattern(currentMirrorPattern);
                result += mirrorPattern.calculateSmudgedMirrorPositions(1);
                currentMirrorPattern = new ArrayList<>();
            } else {
                currentMirrorPattern.add(ParseUtils.parseLineToBooleanList(lines.get(i), '#'));
            }
        }
        LOGGER.info("Result puzzle 26: {}", result);
        return result;
    }

    public int puzzle141() {
        List<String> lines = lineReader.readLines("advent_file_14.txt");
        List<Coordinates> fixedRockPositions = new ArrayList<>();
        List<Coordinates> rollingRockPositions = new ArrayList<>();
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            fixedRockPositions.addAll(ParseUtils.parseLineToCoordinates(line, y, '#'));
            rollingRockPositions.addAll(ParseUtils.parseLineToCoordinates(line, y, 'O'));
        }
        TiltedRockPlatform tiltedRockPlatform = new TiltedRockPlatform(fixedRockPositions, rollingRockPositions,
                Coordinates.getMaxCoordinates(lines));
        tiltedRockPlatform.tilt(Direction.NORTH, 0);
        int result = tiltedRockPlatform.calculateCurrentLoad(Direction.NORTH);
        LOGGER.info("Result puzzle 27: {}", result);
        return result;
    }

    public int puzzle142() {
        List<String> lines = lineReader.readLines("advent_file_14.txt");
        List<Coordinates> fixedRockPositions = new ArrayList<>();
        List<Coordinates> rollingRockPositions = new ArrayList<>();
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            fixedRockPositions.addAll(ParseUtils.parseLineToCoordinates(line, y, '#'));
            rollingRockPositions.addAll(ParseUtils.parseLineToCoordinates(line, y, 'O'));
        }
        TiltedRockPlatform tiltedRockPlatform = new TiltedRockPlatform(fixedRockPositions, rollingRockPositions,
                Coordinates.getMaxCoordinates(lines));
        long cycle = 1;
        while (!tiltedRockPlatform.isLoopFound()) {
            final long finalCycle = cycle;
            Arrays.stream(Direction.values()).forEach(d -> tiltedRockPlatform.tilt(d, finalCycle));
            cycle++;
        }
        int result = 0;
        for (Map.Entry<RockPositionList, Long> loopPoint : tiltedRockPlatform.getLoopCycleMap().entrySet()) {
            if ((1000000000 - loopPoint.getValue()) % tiltedRockPlatform.getLoopSize() == 0) {
                result = loopPoint.getKey().calculateLoad(Direction.NORTH, tiltedRockPlatform.getMaxC());
            }
        }
        LOGGER.info("Result puzzle 28: {}", result);
        return result;
    }

    public int puzzle151() {
        List<String> lines = lineReader.readLines("advent_file_15.txt");
        List<String> steps = lines.stream().flatMap(l -> Arrays.stream(l.split(","))).toList();
        int result = steps.stream().mapToInt(Lens::calculateHash).sum();
        LOGGER.info("Result puzzle 29: {}", result);
        return result;
    }

    public int puzzle152() {
        List<String> lines = lineReader.readLines("advent_file_15.txt");
        List<String> steps = lines.stream().flatMap(l -> Arrays.stream(l.split(","))).toList();
        Map<Integer, Box> boxes = new HashMap<>();
        IntStream.range(0, 256).forEach(hash -> boxes.put(hash, new Box(hash)));
        steps.stream().map(Lens::parseLens).forEach(lens -> {
            Box box = boxes.get(lens.hashCode());
            if (lens.focus() == null) {
                box.removeLens(lens);
            } else {
                box.addLens(lens);
            }
        });
        int result = boxes.values().stream().mapToInt(Box::calculateBoxFocusPower).sum();
        LOGGER.info("Result puzzle 30: {}", result);
        return result;
    }
}

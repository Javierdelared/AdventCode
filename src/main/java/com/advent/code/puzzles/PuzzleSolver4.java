package com.advent.code.puzzles;

import com.advent.code.models.*;
import com.advent.code.utils.ParseUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class PuzzleSolver4 extends PuzzleSolver {

    public PuzzleSolver4(String basePath) {
        super(basePath);
    }

    public long puzzle161() {
        List<String> lines = lineReader.readLines("advent_file_16.txt");
        Map<Coordinates, Mirror> mirrorPositions = new HashMap<>();
        for (int y = 0; y < lines.size(); y++) {
            final int yFinal = y;
            Arrays.stream(Mirror.values()).forEach(m ->
                ParseUtils.parseLineToCoordinates(lines.get(yFinal), yFinal, m.symbol).forEach(c ->
                        mirrorPositions.put(c, m)));
        }
        MirrorContraption mirrorContraption = new MirrorContraption(mirrorPositions, Coordinates.getMaxCoordinates(lines));
        mirrorContraption.sendLight(new Vector(Coordinates.ORIGIN, Direction.EAST, 1));
        int result = mirrorContraption.getNumberEnergisedTiles();
        LOGGER.info("Result puzzle 31: {}", result);
        return result;
    }

    public long puzzle162() {
        List<String> lines = lineReader.readLines("advent_file_16.txt");
        Map<Coordinates, Mirror> mirrorPositions = new HashMap<>();
        for (int y = 0; y < lines.size(); y++) {
            final int yFinal = y;
            Arrays.stream(Mirror.values()).forEach(m ->
                    ParseUtils.parseLineToCoordinates(lines.get(yFinal), yFinal, m.symbol).forEach(c ->
                            mirrorPositions.put(c, m)));
        }
        MirrorContraption mirrorContraption = new MirrorContraption(mirrorPositions, Coordinates.getMaxCoordinates(lines));
        int result = mirrorContraption.calculateSendLightEdges().stream().mapToInt(v -> {
                    mirrorContraption.clean();
                    mirrorContraption.sendLight(v);
                    return mirrorContraption.getNumberEnergisedTiles();
                }).max().orElse(0);
        LOGGER.info("Result puzzle 31: {}", result);
        return result;
    }

    public int puzzle171() {
        List<String> lines = lineReader.readLines("advent_file_17.txt");
        Map<Coordinates, Integer> map = new HashMap<>();
        IntStream.range(0, lines.size()).mapToObj(y -> ParseUtils.parseLineToCoordinatesAndIntegerMap(lines.get(y), y))
                .forEach(map::putAll);
        HeatMap heatMap = new HeatMap(map, 1,3, Coordinates.ORIGIN,
                Coordinates.getBottomRightCoordinates(lines));
        int result = heatMap.calculateMinHeat();
        LOGGER.info("Result puzzle 31: {}", result);
        return result;
    }

    public int puzzle172() {
        List<String> lines = lineReader.readLines("advent_file_17.txt");
        Map<Coordinates, Integer> map = new HashMap<>();
        IntStream.range(0, lines.size()).mapToObj(y -> ParseUtils.parseLineToCoordinatesAndIntegerMap(lines.get(y), y))
                .forEach(map::putAll);
        HeatMap heatMap = new HeatMap(map, 4,10, Coordinates.ORIGIN, Coordinates.getBottomRightCoordinates(lines));
        int result = heatMap.calculateMinHeat();
        LOGGER.info("Result puzzle 32: {}", result);
        return result;
    }

    public long puzzle181() {
        List<String> lines = lineReader.readLines("advent_file_18.txt");
        LavaPit lavaPit = new LavaPit();
        lavaPit.parseBorders(lines);
        long result = lavaPit.calculateArea();
        LOGGER.info("Result puzzle 33: {}", result);
        return result;
    }

    public long puzzle182() {
        List<String> lines = lineReader.readLines("advent_file_18.txt");
        LavaPit lavaPit = new LavaPit();
        lavaPit.parseBordersColours(lines);
        long result = lavaPit.calculateArea();
        LOGGER.info("Result puzzle 34: {}", result);
        return result;
    }
}

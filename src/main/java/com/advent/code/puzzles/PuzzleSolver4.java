package com.advent.code.puzzles;

import com.advent.code.models.*;
import com.advent.code.utils.LineReader;
import com.advent.code.utils.ParseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuzzleSolver4 {
    private static final Logger LOGGER = LoggerFactory.getLogger(PuzzleSolver4.class);

    private final LineReader lineReader;

    public PuzzleSolver4(String basePath) {
        this.lineReader = new LineReader(basePath);
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
        mirrorContraption.sendLight(new Vector(Coordinates.ORIGIN, Direction.EAST));
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
}

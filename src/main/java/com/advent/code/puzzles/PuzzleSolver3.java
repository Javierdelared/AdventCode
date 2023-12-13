package com.advent.code.puzzles;

import com.advent.code.models.*;
import com.advent.code.utils.LineReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class PuzzleSolver3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(PuzzleSolver3.class);

    private final LineReader lineReader;

    public PuzzleSolver3(String basePath) {
        this.lineReader = new LineReader(basePath);
    }

    public long puzzle111() {
        long distance = puzzle112(2);
        LOGGER.info("Result puzzle 21: {}", distance);
        return distance;
    }

    public long puzzle112(int spaceMultiplier) {
        List<String> lines = lineReader.readLines("advent_file_11.txt");
        List<List<Boolean>> imageGalaxyPositions = lines.stream().map(GalaxyMap::parseGalaxyPositions).toList();
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
}

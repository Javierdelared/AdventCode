package com.advent.code.puzzles;

import com.advent.code.models.*;
import com.advent.code.utils.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class PuzzleSolver2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(PuzzleSolver2.class);
    public static long puzzle61() {
        List<String> lines = FileReader.lineReader("advent_file_6.txt");
        List<Race> races = Race.parseRaces(lines);
        long result = races.stream().map(Race::calculatePossibleTimes).reduce(1L, (a, b) -> a * b);
        LOGGER.info("Result puzzle 11: {}", result);
        return result;
    }

    public static long puzzle62() {
        List<String> lines = FileReader.lineReader("advent_file_6.txt");
        Race race = Race.parseRace(lines);
        long result = race.calculatePossibleTimes();
        LOGGER.info("Result puzzle 12: {}", result);
        return result;
    }

}

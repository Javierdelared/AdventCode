package com.advent.code.puzzles;

import com.advent.code.models.*;
import com.advent.code.utils.LineReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class PuzzleSolver2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(PuzzleSolver2.class);

    private final LineReader lineReader;

    public PuzzleSolver2(LineReader lineReader) {
        this.lineReader = lineReader;
    }

    public long puzzle61() {
        List<String> lines = lineReader.readLines("advent_file_6.txt");
        List<Race> races = Race.parseRaces(lines);
        long result = races.stream().map(Race::calculatePossibleTimes).reduce(1L, (a, b) -> a * b);
        LOGGER.info("Result puzzle 11: {}", result);
        return result;
    }

    public long puzzle62() {
        List<String> lines = lineReader.readLines("advent_file_6.txt");
        Race race = Race.parseRace(lines);
        long result = race.calculatePossibleTimes();
        LOGGER.info("Result puzzle 12: {}", result);
        return result;
    }

    public long puzzle71() {
        List<String> lines = lineReader.readLines("advent_file_7.txt");
        List<CamelHand> hands = new ArrayList<>(lines.stream().map(l -> CamelHand.parseCamelHand(l, false)).toList());
        Collections.sort(hands);
        int rank = 1;
        long result = 0;
        for(CamelHand hand : hands) {
            result += rank++ * hand.getBet();
        }
        LOGGER.info("Result puzzle 13: {}", result);
        return result;
    }

    public long puzzle72() {
        List<String> lines = lineReader.readLines("advent_file_7.txt");
        List<CamelHand> hands = new ArrayList<>(lines.stream().map(l -> CamelHand.parseCamelHand(l, true)).toList());
        Collections.sort(hands);
        int rank = 1;
        long result = 0;
        for (CamelHand hand : hands) {
            result += rank++ * hand.getBet();
        }
        LOGGER.info("Result puzzle 14: {}", result);
        return result;
    }
}

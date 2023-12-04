package com.advent.code;

import com.advent.code.dto.*;
import com.advent.code.utils.FileReader;
import com.advent.code.utils.StringOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PuzzleSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PuzzleSolver.class);

    public static void puzzle1() {
        List<String> lines = FileReader.lineReader("advent_file_1.txt");
        int result = lines.stream().mapToInt(StringOperator::getCalibrationValue).sum();
        LOGGER.info("Result puzzle 1: {}", result);
    }

    public static void puzzle2() {
        List<String> lines = FileReader.lineReader("advent_file_1.txt");
        int result = lines.stream().mapToInt(StringOperator::getCalibrationValueExtended).sum();
        LOGGER.info("Result puzzle 2: {}", result);
    }

    public static void puzzle3() {
        GameSet maxGameSet = new GameSet(12, 13, 14);
        List<String> lines = FileReader.lineReader("advent_file_2.txt");
        int result = lines.stream().map(StringOperator::getGame)
                .filter(game -> game.isValidGame(maxGameSet))
                .mapToInt(Game::getGameID).sum();
        LOGGER.info("Result puzzle 3: {}", result);
    }

    public static void puzzle4() {
        List<String> lines = FileReader.lineReader("advent_file_2.txt");
        int result = lines.stream().map(StringOperator::getGame)
                .mapToInt(Game::calculateMinimumSetPower).sum();
        LOGGER.info("Result puzzle 4: {}", result);
    }

    public static void puzzle5() {
        List<String> lines = FileReader.lineReader("advent_file_3.txt");
        List<Position> symbolPositions = new ArrayList<>();
        List<NumberLocation> numberLocationList = new ArrayList<>();
        IntStream.range(0, lines.size()).forEach(i -> {
            String line = lines.get(i);
            symbolPositions.addAll(StringOperator.getSymbolPositions(i, line));
            numberLocationList.addAll(StringOperator.getNumbersLocations(i, line));
        });
        int result = numberLocationList.stream()
                .filter(nl -> nl.hasAdjacent(symbolPositions))
                .mapToInt(NumberLocation::getNumberValue).sum();
        LOGGER.info("Result puzzle 5: {}", result);
    }

    public static void puzzle6() {
        List<String> lines = FileReader.lineReader("advent_file_3.txt");
        List<Position> gearsPositions = new ArrayList<>();
        List<NumberLocation> numberLocationList = new ArrayList<>();
        IntStream.range(0, lines.size()).forEach(i -> {
            String line = lines.get(i);
            gearsPositions.addAll(StringOperator.getGearPositions(i, line));
            numberLocationList.addAll(StringOperator.getNumbersLocations(i, line));
        });
        int result = gearsPositions.stream()
                .map(g -> g.getAdjacentNumbers(numberLocationList))
                .filter(l -> l.size() == 2)
                .mapToInt(l -> l.get(0) * l.get(1)).sum();
        LOGGER.info("Result puzzle 6: {}", result);
    }

    public static void puzzle7() {
        List<String> lines = FileReader.lineReader("advent_file_4.txt");
        int result = lines.stream().map(StringOperator::getScratchCard).mapToInt(ScratchCard::calculatePoints).sum();
        LOGGER.info("Result puzzle 7: {}", result);
    }

    public static void puzzle8() {
        List<String> lines = FileReader.lineReader("advent_file_4.txt");
        SortedMap<Integer, ScratchCard> scratchCardMap = lines.stream().map(StringOperator::getScratchCard)
                .collect(Collectors.toMap(ScratchCard::getCardId, card -> card, (a, b) -> b, TreeMap::new));
        scratchCardMap.forEach((id, scratchCard) ->
                IntStream.rangeClosed(id + 1, id + scratchCard.getMatchingNumbers())
                        .forEach(j -> scratchCardMap.get(j).addCopies(scratchCard.getCopies())));
        int result = scratchCardMap.values().stream().mapToInt(ScratchCard::getCopies).sum();
        LOGGER.info("Result puzzle 8: {}", result);
    }
}

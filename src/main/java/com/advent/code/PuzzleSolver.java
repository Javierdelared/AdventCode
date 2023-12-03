package com.advent.code;

import com.advent.code.dto.Game;
import com.advent.code.dto.GameSet;
import com.advent.code.dto.NumberLocation;
import com.advent.code.dto.Position;
import com.advent.code.utils.FileReader;
import com.advent.code.utils.StringOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            symbolPositions.addAll(StringOperator.getSymbolPositions(i, line));
            numberLocationList.addAll(StringOperator.getNumbersLocations(i, line));
        }
        int result = numberLocationList.stream()
                .filter(nl -> nl.hasAdjacent(symbolPositions))
                .mapToInt(NumberLocation::getNumberValue).sum();
        LOGGER.info("Result puzzle 5: {}", result);
    }

    public static void puzzle6() {
        List<String> lines = FileReader.lineReader("advent_file_3.txt");
        List<Position> gearsPositions = new ArrayList<>();
        List<NumberLocation> numberLocationList = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            gearsPositions.addAll(StringOperator.getGearPositions(i, line));
            numberLocationList.addAll(StringOperator.getNumbersLocations(i, line));
        }
        int result = gearsPositions.stream()
                .map(g -> g.getAdjacentNumbers(numberLocationList))
                .filter(l -> l.size() == 2)
                .mapToInt(l -> l.get(0) * l.get(1)).sum();
        LOGGER.info("Result puzzle 6: {}", result);
    }
}

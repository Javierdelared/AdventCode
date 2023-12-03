package com.advent.code.utils;

import com.advent.code.dto.Game;
import com.advent.code.dto.GameSet;
import com.advent.code.dto.NumberLocation;
import com.advent.code.dto.Position;
import com.advent.code.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringOperator {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringOperator.class);

    private final static Pattern patternDigits = Pattern.compile("[0-9]");
    private final static Pattern patternDigitsGreedy = Pattern.compile(".*([0-9])");
    private final static Pattern patternDigitsExtended = Pattern.compile("[0-9]|one|two|three|four|five|six|seven|eight|nine");
    private final static Pattern patternDigitsExtendedGreedy = Pattern.compile(".*([0-9]|one|two|three|four|five|six|seven|eight|nine)");
    private final static Pattern patternGame = Pattern.compile("Game ([0-9]*): ");
    private final static Pattern patternNumbers = Pattern.compile("[0-9]+");
    private final static Pattern patternNotDigitsOrPoints = Pattern.compile("(?!([0-9]|\\.)).");
    public static Map<String, String> NUMBERS = Map.of("one", "1", "two", "2",
            "three", "3", "four", "4", "five", "5", "six", "6",
            "seven", "7", "eight", "8", "nine", "9");
    public static int getCalibrationValue(String line) {
        StringBuilder stringDigitsBuilder = new StringBuilder();
        Matcher matcherFirst = patternDigits.matcher(line);
        if (matcherFirst.find()) {
            stringDigitsBuilder.append(matcherFirst.group());
        }
        Matcher matcherLast = patternDigitsGreedy.matcher(line);
        if (matcherLast.find()) {
            stringDigitsBuilder.append(matcherLast.group(1));
        }
        String stringDigits = stringDigitsBuilder.toString();
        LOGGER.debug("Calibration value: {}", stringDigits);
        return Integer.parseInt(stringDigits);
    }

    public static int getCalibrationValueExtended(String line) {
        StringBuilder stringDigitsBuilder = new StringBuilder();
        Matcher matcherFirst = patternDigitsExtended.matcher(line);
        if (matcherFirst.find()) {
            stringDigitsBuilder.append(matcherFirst.group());
        }
        Matcher matcherLast = patternDigitsExtendedGreedy.matcher(line);
        if (matcherLast.find()) {
            stringDigitsBuilder.append(matcherLast.group(1));
        }
        String stringDigits = stringDigitsBuilder.toString();
        for (Map.Entry<String, String> n : NUMBERS.entrySet()) {
            stringDigits = stringDigits.replaceAll(n.getKey(), n.getValue());
        }
        LOGGER.debug("Calibration value: {}", stringDigits);
        return Integer.parseInt(stringDigits);
    }

    public static Game getGame(String line) {
        Game game = new Game();
        Matcher matcherGame = patternGame.matcher(line);
        if (matcherGame.find()) {
            Integer gameID = Integer.parseInt(matcherGame.group(1));
            game.setGameID(gameID);
            line = line.replace(matcherGame.group(), "");
        } else {
            throw new ServiceException("Game ID not found");
        }
        List<String> sets = List.of(line.split("; "));
        for (String s : sets) {
            GameSet gameSet = new GameSet();
            List<String> colorCubes = List.of(s.split(", "));
            for (String colorCube : colorCubes) {
                String[] colorCubeArray = colorCube.split(" ");
                gameSet.addColour(colorCubeArray[1], Integer.parseInt(colorCubeArray[0]));
            }
            game.addSet(gameSet);
        }
        return game;
    }

    public static List<NumberLocation> getNumbersLocations(int lineNumber, String line) {
        Matcher matcher = patternNumbers.matcher(line);
        List<NumberLocation> numberLocationList = new ArrayList<>();
        while (matcher.find()) {
            NumberLocation numberLocation = new NumberLocation(Integer.parseInt(matcher.group()),
                    matcher.start(), lineNumber, matcher.group().length());
            numberLocationList.add(numberLocation);
        }
        return numberLocationList;
    }

    public static List<Position> getSymbolPositions(int lineNumber, String line) {
        Matcher matcher = patternNotDigitsOrPoints.matcher(line);
        List<Position> symbolPositions = new ArrayList<>();
        while (matcher.find()) {
            symbolPositions.add(new Position(matcher.start(), lineNumber));
        }
        return symbolPositions;
    }
}
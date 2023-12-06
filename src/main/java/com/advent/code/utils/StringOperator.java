package com.advent.code.utils;

import com.advent.code.dto.*;
import com.advent.code.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringOperator {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringOperator.class);

    private final static Pattern patternDigits = Pattern.compile("[0-9]");
    private final static Pattern patternDigitsGreedy = Pattern.compile(".*([0-9])");
    private final static Pattern patternDigitsExtended = Pattern.compile("[0-9]|one|two|three|four|five|six|seven|eight|nine");
    private final static Pattern patternDigitsExtendedGreedy = Pattern.compile(".*([0-9]|one|two|three|four|five|six|seven|eight|nine)");
    private final static Pattern patternGame = Pattern.compile("^Game ([0-9]*): ");
    private final static Pattern patternScratchCard = Pattern.compile("^Card +([0-9]*):");
    private final static Pattern patternNumbers = Pattern.compile("[0-9]+");
    private final static Pattern patternNotDigitsOrPoints = Pattern.compile("(?!([0-9]|\\.)).");
    private final static Pattern patternAsterisk = Pattern.compile("\\*");
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
            int gameID = Integer.parseInt(matcherGame.group(1));
            game.setGameID(gameID);
            line = line.replace(matcherGame.group(), "");
        } else {
            throw new ServiceException("Game ID not found");
        }
        List<String> sets = List.of(line.split("; "));
        for (String set : sets) {
            GameSet gameSet = new GameSet();
            Stream.of(set.split(", ")).map(colorCube -> colorCube.split(" "))
                    .forEach(arr -> gameSet.addColour(arr[1], Integer.parseInt(arr[0])));
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

    public static List<Long> getLongNumbers(String line) {
        return Stream.of(line.split(" ")).filter(s -> s.matches("[0-9]+"))
                .map(Long::parseLong).toList();
    }

    public static Long getLongNumber(String line) {
        return Long.parseLong(Stream.of(line.split(" ")).filter(s -> s.matches("[0-9]+"))
                .collect(Collectors.joining("")));
    }

    public static List<Position> getGearPositions(int lineNumber, String line) {
        Matcher matcher = patternAsterisk.matcher(line);
        List<Position> gearPositions = new ArrayList<>();
        while (matcher.find()) {
            gearPositions.add(new Position(matcher.start(), lineNumber));
        }
        return gearPositions;
    }

    public static ScratchCard getScratchCard(String line) {
        ScratchCard scratchCard = new ScratchCard();
        Matcher matcherGame = patternScratchCard.matcher(line);
        if (matcherGame.find()) {
            int cardId = Integer.parseInt(matcherGame.group(1));
            scratchCard.setCardId(cardId);
            line = line.replace(matcherGame.group(), "");
        } else {
            throw new ServiceException("Card ID not found");
        }
        scratchCard.setWiningNumbers(Stream.of(line.split("\\|")[0].split(" ")).filter(s -> !s.isBlank())
                .map(Integer::parseInt).toList());
        scratchCard.setCardNumbers(Stream.of(line.split("\\|")[1].split(" ")).filter(s -> !s.isBlank())
                .map(Integer::parseInt).toList());
        return scratchCard;
    }

    public static List<Race> getRaces(List<String> lines) {
        List<Race> races = new ArrayList<>();
        List<Long> times = getLongNumbers(lines.get(0));
        List<Long> distances = getLongNumbers(lines.get(1));
        for (int i = 0; i < times.size(); i++) {
            races.add(new Race(times.get(i), distances.get(i)));
        }
        return races;
    }

    public static Race getRace(List<String> lines) {
        return new Race(getLongNumber(lines.get(0)), getLongNumber(lines.get(1)));
    }
}
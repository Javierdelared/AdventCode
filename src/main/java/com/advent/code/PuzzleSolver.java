package com.advent.code;

import com.advent.code.dto.*;
import com.advent.code.utils.AlmanacUtils;
import com.advent.code.utils.FileReader;
import com.advent.code.utils.StringOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PuzzleSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PuzzleSolver.class);

    public static void puzzle11() {
        List<String> lines = FileReader.lineReader("advent_file_1.txt");
        int result = lines.stream().mapToInt(StringOperator::getCalibrationValue).sum();
        LOGGER.info("Result puzzle 1: {}", result);
    }

    public static void puzzle12() {
        List<String> lines = FileReader.lineReader("advent_file_1.txt");
        int result = lines.stream().mapToInt(StringOperator::getCalibrationValueExtended).sum();
        LOGGER.info("Result puzzle 2: {}", result);
    }

    public static void puzzle21() {
        GameSet maxGameSet = new GameSet(12, 13, 14);
        List<String> lines = FileReader.lineReader("advent_file_2.txt");
        int result = lines.stream().map(StringOperator::getGame)
                .filter(game -> game.isValidGame(maxGameSet))
                .mapToInt(Game::getGameID).sum();
        LOGGER.info("Result puzzle 3: {}", result);
    }

    public static void puzzle22() {
        List<String> lines = FileReader.lineReader("advent_file_2.txt");
        int result = lines.stream().map(StringOperator::getGame)
                .mapToInt(Game::calculateMinimumSetPower).sum();
        LOGGER.info("Result puzzle 4: {}", result);
    }

    public static void puzzle31() {
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

    public static void puzzle32() {
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

    public static void puzzle41() {
        List<String> lines = FileReader.lineReader("advent_file_4.txt");
        int result = lines.stream().map(StringOperator::getScratchCard).mapToInt(ScratchCard::calculatePoints).sum();
        LOGGER.info("Result puzzle 7: {}", result);
    }

    public static void puzzle42() {
        List<String> lines = FileReader.lineReader("advent_file_4.txt");
        SortedMap<Integer, ScratchCard> scratchCardMap = lines.stream().map(StringOperator::getScratchCard)
                .collect(Collectors.toMap(ScratchCard::getCardId, card -> card, (a, b) -> b, TreeMap::new));
        scratchCardMap.forEach((id, scratchCard) ->
                IntStream.rangeClosed(id + 1, id + scratchCard.getMatchingNumbers())
                        .forEach(j -> scratchCardMap.get(j).addCopies(scratchCard.getCopies())));
        int result = scratchCardMap.values().stream().mapToInt(ScratchCard::getCopies).sum();
        LOGGER.info("Result puzzle 8: {}", result);
    }

    public static void puzzle51() {
        List<String> lines = FileReader.lineReader("advent_file_5.txt");
        List<Long> seeds = AlmanacUtils.getSeeds(lines.get(0));
        Map<String, AlmanacMap> almanacMaps = AlmanacUtils.getAlmanacMaps(lines);
        String source = "seed";
        while (!"location".equals(source)) {
            AlmanacMap currentMap = almanacMaps.get(source);
            seeds = seeds.stream().map(currentMap::calculateDestination).toList();
            source = currentMap.getDestination();
        }
        long result = Collections.min(seeds);
        LOGGER.info("Result puzzle 9: {}", result);
    }

    public static void puzzle52() {
        List<String> lines = FileReader.lineReader("advent_file_5.txt");
        Map<String, AlmanacMap> almanacMaps = AlmanacUtils.getAlmanacMaps(lines);
        AlmanacMap almanacSeeds = AlmanacUtils.getSeedRanges(lines.get(0));
        List<MapRange> currentMapRanges = almanacSeeds.getMapRanges();
        String source = "seed";
        while (!"location".equals(source)) {
            AlmanacMap newAlmanacMap = almanacMaps.get(source);
            currentMapRanges = AlmanacUtils.getNewAlmanacMapRanges(currentMapRanges, newAlmanacMap.getMapRanges());
            source = newAlmanacMap.getDestination();
        }
        long result = Collections.min(currentMapRanges.stream().map(MapRange::getDestinationFirstElement).toList());
        LOGGER.info("Result puzzle 10: {}", result);
    }

}

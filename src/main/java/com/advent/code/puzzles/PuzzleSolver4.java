package com.advent.code.puzzles;

import com.advent.code.models.*;
import com.advent.code.models.Vector;
import com.advent.code.utils.MathUtils;
import com.advent.code.utils.ParseUtils;

import java.util.*;
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
        LOGGER.info("Result puzzle 32: {}", result);
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
        LOGGER.info("Result puzzle 33: {}", result);
        return result;
    }

    public int puzzle172() {
        List<String> lines = lineReader.readLines("advent_file_17.txt");
        Map<Coordinates, Integer> map = new HashMap<>();
        IntStream.range(0, lines.size()).mapToObj(y -> ParseUtils.parseLineToCoordinatesAndIntegerMap(lines.get(y), y))
                .forEach(map::putAll);
        HeatMap heatMap = new HeatMap(map, 4,10, Coordinates.ORIGIN, Coordinates.getBottomRightCoordinates(lines));
        int result = heatMap.calculateMinHeat();
        LOGGER.info("Result puzzle 34: {}", result);
        return result;
    }

    public long puzzle181() {
        List<String> lines = lineReader.readLines("advent_file_18.txt");
        LavaPit lavaPit = new LavaPit();
        lavaPit.parseBorders(lines);
        long result = lavaPit.calculateArea();
        LOGGER.info("Result puzzle 35: {}", result);
        return result;
    }

    public long puzzle182() {
        List<String> lines = lineReader.readLines("advent_file_18.txt");
        LavaPit lavaPit = new LavaPit();
        lavaPit.parseBordersColours(lines);
        long result = lavaPit.calculateArea();
        LOGGER.info("Result puzzle 36: {}", result);
        return result;
    }

    public long puzzle191() {
        List<String> lines = lineReader.readLines("advent_file_19.txt");
        List<MachinePart> machineParts = new ArrayList<>();
        MachinePartWorkFlowMap machinePartWorkFlow = new MachinePartWorkFlowMap();
        lines.stream().filter(line -> !line.equals("")).forEach(line -> {
            if (line.charAt(0) == '{') {
                machineParts.add(MachinePart.parseMachinePart(line));
            } else {
                machinePartWorkFlow.add(MachinePartWorkFlow.parseWorkflow(line));
            }
        });
        long result = machineParts.stream().filter(m -> machinePartWorkFlow.executeWorkflow(m, "in") != null)
                .mapToInt(MachinePart::calculateTotalRating).sum();
        LOGGER.info("Result puzzle 37: {}", result);
        return result;
    }

    public long puzzle192() {
        List<String> lines = lineReader.readLines("advent_file_19.txt");
        MachinePartWorkFlowMap machinePartWorkFlowMap = new MachinePartWorkFlowMap();
        lines.stream().filter(line -> !line.equals("") && !(line.charAt(0) == '{'))
                .map(MachinePartWorkFlow::parseWorkflow).forEach(machinePartWorkFlowMap::add);
        List<MachinePartRange> ranges = machinePartWorkFlowMap.executeWorkflowRange(
                List.of(MachinePartRange.FULL_RANGE), "in");
        long result = ranges.stream().mapToLong(MachinePartRange::calculateRangeCombinations).sum();
        LOGGER.info("Result puzzle 38: {}", result);
        return result;
    }

    public long puzzle201() {
        List<String> lines = lineReader.readLines("advent_file_20.txt");
        CommunicationModuleMap current = CommunicationModuleMap.parse(lines);
        CommunicationModuleMap initial = current.copy();
        int cycle = 0;
        do {
            current.sendPulses(cycle, "button", List.of("broadcaster"), false);
            cycle++;
        } while (!initial.equals(current) && cycle < 1000);
        int loops = 1000 / cycle;
        long lowPulses = current.getLowPulses() * loops;
        long highPulses = current.getHighPulses() * loops;
        current.cleanCounters();
        IntStream.range(0, 1000 % cycle).forEach(i ->
                current.sendPulses(i, "button", List.of("broadcaster"), false));
        long result = (lowPulses + current.getLowPulses()) * (highPulses + current.getHighPulses());
        LOGGER.info("Result puzzle 39: {}", result);
        return result;
    }

    public long puzzle202() {
        List<String> lines = lineReader.readLines("advent_file_20.txt");
        CommunicationModuleMap current = CommunicationModuleMap.parse(lines);
        List<String> highSignalModules = List.of("ph","vn","kt","hn");
        int cycle = 1;
        do {
            current.sendPulses(cycle, "button", List.of("broadcaster"), false);
            cycle++;
        } while (cycle < 10000);
        long result = MathUtils.lcm(highSignalModules.stream().map(m -> current.getHighPulseStart().get(m)).toList());
        LOGGER.info("Result puzzle 40: {}", result);
        return result;
    }
}

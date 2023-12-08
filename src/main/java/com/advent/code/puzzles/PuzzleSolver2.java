package com.advent.code.puzzles;

import com.advent.code.models.*;
import com.advent.code.utils.LineReader;
import com.advent.code.utils.MathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class PuzzleSolver2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(PuzzleSolver2.class);

    private final LineReader lineReader;

    public PuzzleSolver2(String basePath) {
        this.lineReader = new LineReader(basePath);
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

    public int puzzle81() {
        List<String> lines = lineReader.readLines("advent_file_8.txt");
        String directions = lines.get(0);
        int directionsLength = directions.length();
        Map<String, DirectionNode> directionNodes = lines.stream().map(DirectionNode::parseDirectionNode)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(DirectionNode::getOrigin, dn -> dn, (a, b) -> b, TreeMap::new));
        String currentPosition = "AAA";
        int step = 0;
        while (!"ZZZ".equals(currentPosition)) {
            char direction = directions.charAt(step % directionsLength);
            currentPosition = directionNodes.get(currentPosition).getNewPosition(direction);
            step++;
        }
        LOGGER.info("Result puzzle 15: {}", step);
        return step;
    }

    public long puzzle82() {
        List<String> lines = lineReader.readLines("advent_file_8.txt");
        String directions = lines.get(0);
        List<DirectionNode> directionNodes = lines.stream().map(DirectionNode::parseDirectionNode)
                .filter(Objects::nonNull).toList();
        List<DirectionNode> startingPositions = directionNodes.stream()
                .filter(x -> x.getOrigin().matches(".{2}A")).toList();
        List<DirectionNodeMatchLoop> startingPositionMatches = startingPositions.stream()
                .map(sp -> new DirectionNodeMatchLoop(sp.getOrigin(), directions, directionNodes, ".{2}Z"))
                .toList();
        long step = 0;
        // Trick to avoid brute force for particular case of perfect loops.
        if (startingPositionMatches.stream().allMatch(DirectionNodeMatchLoop::isPerfectLoop)) {
            step = MathUtils.lcm(startingPositionMatches.stream()
                    .mapToLong(DirectionNodeMatchLoop::getPerfectLoopSize));
        } else {
            DirectionNodeMatchLoop firstDirectionNodeMatchLoop = startingPositionMatches.get(0);
            int i = 0;
            boolean conditionIsReached = false;
            while (!conditionIsReached) {
                step = firstDirectionNodeMatchLoop.findPosition(i);
                final long stepFinal = step;
                conditionIsReached = startingPositionMatches.stream().allMatch(sp -> sp.isValidPosition(stepFinal));
                i++;
            }
        }
        LOGGER.info("Result puzzle 16: {}", step);
        return step;
    }
}

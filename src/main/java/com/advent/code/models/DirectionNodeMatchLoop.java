package com.advent.code.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DirectionNodeMatchLoop {

    private final String origin;
    private final String directions;
    private final Map<String, DirectionNode> directionNodes;
    private final String pattern;
    private List<Integer> matchesSortedList;
    private int loopSize;
    private int startLoop;

    private int perfectLoopSize;

    public int getPerfectLoopSize() {
        return perfectLoopSize;
    }

    public DirectionNodeMatchLoop(String origin, String directions, List<DirectionNode> directionNodes,
                                  String pattern) {
        this.origin = origin;
        this.directions = directions;
        this.directionNodes = directionNodes.stream()
                .collect(Collectors.toMap(DirectionNode::getOrigin, Function.identity()));
        this.pattern = pattern;
        calculateMatches();
    }

    public long findPosition(int n) {
        return (long) loopSize * ( n / matchesSortedList.size()) +
                matchesSortedList.get(n % matchesSortedList.size())
                + startLoop;
    }

    public boolean isValidPosition(long step) {
        return matchesSortedList.contains((int) ((step - startLoop) % loopSize));
    }

    /**
     * Method to calculate if the match loop is a perfect loop
     * We define a perfect loop is a node loop in which the matches are evenly spaced
     * and the loop starts at a step that is equal to the space between matches.
     * This allows to infer that the position of all the matches are multiples of the loop size.
     *
     * @return true if the match loop is a perfect loop, false otherwise.
     */
    public boolean isPerfectLoop() {
        int loopMatches = matchesSortedList.size();
        int loop = loopSize / loopMatches;
        if (startLoop != loop) {
            return false;
        }
        for (int i = 0; i < loopMatches; i++) {
            if (matchesSortedList.get(i) != i*loop) {
                return false;
            }
        }
        perfectLoopSize = loop;
        return true;
    }

    public void calculateMatches() {
        final Map<String, DirectionNodePosition> positionMatches = new HashMap<>();
        String currentPosition = origin;
        int directionsLength = directions.length();
        int step = 0;
        boolean conditionIsReached = false;
        while (!conditionIsReached) {
            char direction = directions.charAt(step++ % directionsLength);
            currentPosition = directionNodes.get(currentPosition).getNewPosition(direction);
            if (currentPosition.matches(pattern)) {
                if (!positionMatches.containsKey(currentPosition)) {
                    positionMatches.put(currentPosition,
                            new DirectionNodePosition(currentPosition, directionsLength, step));
                }
                if (positionMatches.get(currentPosition).isExistingDirectionsPosition(step)) {
                    conditionIsReached = true;
                } else {
                    positionMatches.get(currentPosition).addStep(step);
                }
            }
        }
        startLoop = positionMatches.get(currentPosition).getFirstStep();
        // Get the positions relative to the start of the loop and remove the ones that happened before it started.
        matchesSortedList = positionMatches.values().stream()
                .flatMap(m -> m.getSteps().stream().map(s -> s - startLoop))
                .filter(s -> s >= 0).sorted().toList();
        loopSize = step - startLoop;
    }
}

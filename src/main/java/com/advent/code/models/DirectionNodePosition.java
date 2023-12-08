package com.advent.code.models;

import java.util.ArrayList;
import java.util.List;

public class DirectionNodePosition {
    private final String directionNode;
    private final int directionsLength;

    private final int firstStep;

    private final List<Integer> steps  = new ArrayList<>();

    public int getFirstStep() {
        return firstStep;
    }

    public List<Integer> getSteps() {
        return steps;
    }

    public DirectionNodePosition(String directionNode, int directionsLength, int firstStep) {
        this.directionNode = directionNode;
        this.directionsLength = directionsLength;
        this.firstStep = firstStep;
    }

    public void addStep(int step) {
        steps.add(step);
    }

    public boolean isExistingDirectionsPosition(int step) {
        return steps.stream().anyMatch(s -> (s - step) % directionsLength == 0);
    }
}

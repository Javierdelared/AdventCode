package com.advent.code.models;

import com.advent.code.exception.ServiceException;

public class PipeMovement {

    private final Direction fromDirection;
    private final PipePosition pipePosition;

    public Direction getFromDirection() {
        return fromDirection;
    }

    public PipePosition getMazePosition() {
        return pipePosition;
    }

    public PipeMovement(Direction direction, PipePosition pipePosition) {
        this.fromDirection = direction;
        this.pipePosition = pipePosition;
    }

    public Direction nextDirection() {
        return pipePosition.getDirections().stream()
                .filter(d -> !d.equals(fromDirection.getOpposite())).findFirst()
                .orElseThrow(() -> new ServiceException("No next direction found"));
    }
}

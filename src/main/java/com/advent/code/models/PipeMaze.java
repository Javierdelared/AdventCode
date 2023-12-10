package com.advent.code.models;

import com.advent.code.exception.ServiceException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.advent.code.models.Direction.*;
import static com.advent.code.models.Direction.WEST;

public class PipeMaze {

    private final List<PipePosition> mazeMatrix;
    private Map<Coordinates, PipePosition> mazeMatrixMap;
    private PipePosition startPosition;
    private List<PipeMovement> firstMovements;
    private Map<Coordinates, PipeMovement> loopMovementsMap;
    private List<Coordinates> loopCoordinates;

    public Map<Coordinates, PipePosition> getMazeMatrixMap() {
        if (mazeMatrixMap == null) {
            mazeMatrixMap = mapPipePositions();
        }
        return mazeMatrixMap;
    }

    public PipePosition getStartPosition() {
        if (startPosition == null) {
            startPosition = calculateStartPosition();
        }
        return startPosition;
    }

    public  List<PipeMovement> getFirstMovements() {
        if (firstMovements == null) {
            firstMovements = findFirstMovements();
        }
        return firstMovements;
    }
    public Map<Coordinates, PipeMovement> getLoopMovementsMap() {
        if (loopMovementsMap == null) {
            findLoop();
        }
        return loopMovementsMap;
    }

    public List<Coordinates> getLoopCoordinates() {
        if (loopCoordinates == null) {
            findLoop();
        }
        return loopCoordinates;
    }

    public PipeMaze(List<PipePosition> mazeMatrix) {
        this.mazeMatrix = mazeMatrix;
    }

    private PipePosition calculateStartPosition() {
        return mazeMatrix.stream().filter(x -> 'S' == x.getSymbol()).findFirst()
                .orElseThrow(() -> new ServiceException("Start not found"));
    }

    private Map<Coordinates, PipePosition> mapPipePositions() {
        return mazeMatrix.stream().collect(Collectors.toMap(PipePosition::getCoordinates, Function.identity()));
    }

    private List<PipeMovement> findFirstMovements() {
        List<PipeMovement> fm = new ArrayList<>();
        for (Direction d : Direction.values()) {
            PipePosition positionForward = getMazeMatrixMap().get(getStartPosition().getCoordinates().move(d));
            if (positionForward != null && positionForward.isPipeConnected(d.getOpposite())) {
                fm.add(new PipeMovement(d, positionForward));
            }
        }
        if (fm.size() != 2) {
            throw new ServiceException("No possible first movements found");
        }
        return fm;
    }

    public PipeMovement moveFrom(PipeMovement p) {
        Direction nextDirection = p.nextDirection();
        PipePosition positionForward = getMazeMatrixMap().get(p.getMazePosition().getCoordinates().move(nextDirection));
        if (positionForward != null && positionForward.isPipeConnected(nextDirection.getOpposite())) {
            return new PipeMovement(nextDirection, positionForward);
        }
        throw new ServiceException("Pipes are not connected");
    }

    private void findLoop() {
        LinkedList<PipeMovement> lm = new LinkedList<>();
        lm.add(getFirstMovements().get(0));
        while(lm.getLast().getMazePosition().getSymbol() != 'S') {
            lm.add(moveFrom(lm.getLast()));
        }
        lm.getLast().getMazePosition().setNewDirections(getFirstMovements().stream()
                .map(PipeMovement::getFromDirection).collect(Collectors.toSet()));
        loopMovementsMap = lm.stream().collect(
                Collectors.toMap(m -> m.getMazePosition().getCoordinates(), Function.identity()));
        loopCoordinates = lm.stream().map(pm -> pm.getMazePosition().getCoordinates()).toList();
    }

    public PipeMovement findClosestPipe(Coordinates coordinates) {
        Coordinates pipeCoordinates = getLoopCoordinates().stream()
                .min(Comparator.comparingInt(a -> a.calculateDistance(coordinates)))
                .orElseThrow(() -> new ServiceException("Closest pipe not found"));
        return getLoopMovementsMap().get(pipeCoordinates);
    }

    public Group findGroup(Coordinates c) {
        if (getLoopCoordinates().contains(c)) {
            return Group.PIPE;
        }
        PipeMovement closestPipe = findClosestPipe(c);
        Coordinates pipeC = closestPipe.getMazePosition().getCoordinates();
        Direction fromDirection = closestPipe.getFromDirection();
        Direction leftHandDirection = fromDirection.turnNinetyDegrees();
        char symbol = closestPipe.getMazePosition().getSymbol();
        return switch (symbol) {
            case '|' -> leftHandDirection.equals(c.approachEastWest(pipeC)) ? Group.RIGHT_HAND : Group.LEFT_HAND;
            case '-' -> leftHandDirection.equals(c.approachNorthSouth(pipeC)) ? Group.RIGHT_HAND : Group.LEFT_HAND;
            case 'L' -> fromDirection.equals(SOUTH) ? Group.RIGHT_HAND : Group.LEFT_HAND;
            case 'J' -> fromDirection.equals(EAST) ? Group.RIGHT_HAND : Group.LEFT_HAND;
            case '7' -> fromDirection.equals(NORTH) ? Group.RIGHT_HAND : Group.LEFT_HAND;
            case 'F' -> fromDirection.equals(WEST) ? Group.RIGHT_HAND : Group.LEFT_HAND;
            default -> throw new ServiceException("Pipe symbol not found");
        };
    }

    public enum Group {
        LEFT_HAND, RIGHT_HAND, PIPE;

        public Group reverse() {
            return switch (this) {
                case LEFT_HAND -> RIGHT_HAND;
                case RIGHT_HAND -> LEFT_HAND;
                default -> throw new ServiceException("Cannot reverse group");
            };
        }
    }
}

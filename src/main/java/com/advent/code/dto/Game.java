package com.advent.code.dto;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Integer gameID;
    private final List<GameSet> gameSets = new ArrayList<>();

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public Game() {
    }

    public void addSet(GameSet gameSet) {
        gameSets.add(gameSet);
    }

    public boolean isValidGame(GameSet maxGameSet) {
        return gameSets.stream().noneMatch(gameSet -> gameSet.isImpossible(maxGameSet));
    }

    public int calculateMinimumSetPower() {
        GameSet minimumSet = new GameSet();
        for (GameSet gameSet : gameSets) {
            minimumSet.setRedCubes(Math.max(minimumSet.getRedCubes(), gameSet.getRedCubes()));
            minimumSet.setGreenCubes(Math.max(minimumSet.getGreenCubes(), gameSet.getGreenCubes()));
            minimumSet.setBlueCubes(Math.max(minimumSet.getBlueCubes(), gameSet.getBlueCubes()));
        }
        return minimumSet.calculatePower();
    }
}

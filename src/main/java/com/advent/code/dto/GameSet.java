package com.advent.code.dto;

import com.advent.code.exception.ServiceException;

public class GameSet {

    private int redCubes = 0;
    private int greenCubes = 0;
    private int blueCubes = 0;

    public int getRedCubes() {
        return redCubes;
    }

    public void setRedCubes(int redCubes) {
        this.redCubes = redCubes;
    }

    public int getGreenCubes() {
        return greenCubes;
    }

    public void setGreenCubes(int greenCubes) {
        this.greenCubes = greenCubes;
    }

    public int getBlueCubes() {
        return blueCubes;
    }

    public void setBlueCubes(int blueCubes) {
        this.blueCubes = blueCubes;
    }

    public GameSet() {
    }

    public GameSet(int redCubes, int greenCubes, int blueCubes) {
        this.redCubes = redCubes;
        this.greenCubes = greenCubes;
        this.blueCubes = blueCubes;
    }

    public void addColour(String colour, Integer number) {
        switch (colour) {
            case "red" -> redCubes = number;
            case "green" -> greenCubes = number;
            case "blue" -> blueCubes = number;
            default -> throw new ServiceException("Colour not found");
        }
    }

    public boolean isImpossible(GameSet maxGameSet) {
        return maxGameSet.getRedCubes() < redCubes ||
            maxGameSet.getGreenCubes() < greenCubes ||
            maxGameSet.getBlueCubes() < blueCubes;
    }

    public int calculatePower() {
        return redCubes * greenCubes * blueCubes;
    }
}

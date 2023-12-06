package com.advent.code.models;

import com.advent.code.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Game {

    private final static Pattern patternGame = Pattern.compile("^Game ([0-9]*): ");

    private int gameID;
    private final List<GameSet> gameSets = new ArrayList<>();

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
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

    public static Game parseGame(String line) {
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
}

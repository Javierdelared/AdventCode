package com.advent.code.puzzles;

import com.advent.code.utils.LineReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PuzzleSolver {

    protected static final Logger LOGGER = LoggerFactory.getLogger(PuzzleSolver.class);

    protected final LineReader lineReader;

    public PuzzleSolver(String basePath) {
        this.lineReader = new LineReader(basePath);
    }
}

package com.advent.code.puzzles;

import com.advent.code.utils.LineReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PuzzleSolver2Test {

    private static PuzzleSolver2 puzzleSolver;

    @BeforeAll
    static void setup() {
        LineReader lineReader = new LineReader();
        lineReader.setBasePath("src/test/data");
        puzzleSolver = new PuzzleSolver2(lineReader);
    }

    @Test
    void testPuzzle61() {
        assertEquals(252000, puzzleSolver.puzzle61());
    }

    @Test
    void testPuzzle62() {
        assertEquals(36992486, puzzleSolver.puzzle62());
    }
}

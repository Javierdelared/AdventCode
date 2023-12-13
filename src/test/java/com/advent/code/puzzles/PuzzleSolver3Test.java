package com.advent.code.puzzles;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PuzzleSolver3Test {

    private static PuzzleSolver3 puzzleSolver;
    private static PuzzleSolver3 puzzleSolverExamplesPart1;
    private static PuzzleSolver3 puzzleSolverExamplesPart2;

    @BeforeAll
    static void setup() {
        puzzleSolver = new PuzzleSolver3("src/main/resources");
        puzzleSolverExamplesPart1 = new PuzzleSolver3("src/test/examples/part1");
        puzzleSolverExamplesPart2 = new PuzzleSolver3("src/test/examples/part2");
    }

    @Test
    void testPuzzle111() {
        assertEquals(10165598L, puzzleSolver.puzzle111());
    }
    @Test
    void testPuzzleExample111() {
        assertEquals(374L, puzzleSolverExamplesPart1.puzzle111());
    }
    @Test
    void testPuzzle112() {
        assertEquals(678728808158L, puzzleSolver.puzzle112(1000000));
    }
    @Test
    void testPuzzleExample112() {
        assertEquals(8410, puzzleSolverExamplesPart2.puzzle112(100));
    }
    @Test
    void testPuzzle121() {
        assertEquals(7843, puzzleSolver.puzzle121());
    }
    @Test
    void testPuzzleExample121() {
        assertEquals(21, puzzleSolverExamplesPart1.puzzle121());
    }

    @Test
    void testPuzzle122() {
        assertEquals(10153896718999L, puzzleSolver.puzzle122());
    }
    @Test
    void testPuzzleExample122() {
        assertEquals(525152, puzzleSolverExamplesPart2.puzzle122());
    }
}

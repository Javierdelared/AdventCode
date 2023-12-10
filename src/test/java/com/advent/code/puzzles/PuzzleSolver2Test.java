package com.advent.code.puzzles;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PuzzleSolver2Test {

    private static PuzzleSolver2 puzzleSolver;
    private static PuzzleSolver2 puzzleSolverExamplesPart1;
    private static PuzzleSolver2 puzzleSolverExamplesPart2;

    @BeforeAll
    static void setup() {
        puzzleSolver = new PuzzleSolver2("src/main/resources");
        puzzleSolverExamplesPart1 = new PuzzleSolver2("src/test/examples/part1");
        puzzleSolverExamplesPart2 = new PuzzleSolver2("src/test/examples/part2");
    }

    @Test
    void testPuzzle61() {
        assertEquals(252000L, puzzleSolver.puzzle61());
    }
    @Test
    void testPuzzleExample61() {
        assertEquals(288L, puzzleSolverExamplesPart1.puzzle61());
    }
    @Test
    void testPuzzle62() {
        assertEquals(36992486L, puzzleSolver.puzzle62());
    }
    @Test
    void testPuzzleExample62() {
        assertEquals(71503L, puzzleSolverExamplesPart2.puzzle62());
    }
    @Test
    void testPuzzle71() {
        assertEquals(251927063L, puzzleSolver.puzzle71());
    }
    @Test
    void testPuzzleExample71() {
        assertEquals(6440L, puzzleSolverExamplesPart1.puzzle71());
    }
    @Test
    void testPuzzle72() {
        assertEquals(255632664L, puzzleSolver.puzzle72());
    }
    @Test
    void testPuzzleExample72() {
        assertEquals(5905L, puzzleSolverExamplesPart2.puzzle72());
    }
    @Test
    void testPuzzle81() {
        assertEquals(23147, puzzleSolver.puzzle81());
    }
    @Test
    void testPuzzleExample81() {
        assertEquals(2, puzzleSolverExamplesPart1.puzzle81());
    }
    @Test
    void testPuzzle82() {
        assertEquals(22289513667691L, puzzleSolver.puzzle82());
    }
    @Test
    void testPuzzleExample82() {
        assertEquals(6L, puzzleSolverExamplesPart2.puzzle82());
    }
    @Test
    void testPuzzle91() {
        assertEquals(2075724761L, puzzleSolver.puzzle91());
    }
    @Test
    void testPuzzleExample91() {
        assertEquals(114L, puzzleSolverExamplesPart1.puzzle91());
    }

    @Test
    void testPuzzle92() {
        assertEquals(1072L, puzzleSolver.puzzle92());
    }
    @Test
    void testPuzzleExample92() {
        assertEquals(2L, puzzleSolverExamplesPart2.puzzle92());
    }
    @Test
    void testPuzzle101() {
        assertEquals(6886, puzzleSolver.puzzle101());
    }
    @Test
    void testPuzzleExample101() {
        assertEquals(8, puzzleSolverExamplesPart1.puzzle101());
    }
    @Test
    void testPuzzle102() {
        assertEquals(371, puzzleSolver.puzzle102());
    }
    @Test
    void testPuzzleExample102() {
        assertEquals(10, puzzleSolverExamplesPart2.puzzle102());
    }
}

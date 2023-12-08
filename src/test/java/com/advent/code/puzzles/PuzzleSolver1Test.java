package com.advent.code.puzzles;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PuzzleSolver1Test {

    private static PuzzleSolver1 puzzleSolver;
    private static PuzzleSolver1 puzzleSolverExamplesPart1;
    private static PuzzleSolver1 puzzleSolverExamplesPart2;

    @BeforeAll
    static void setup() {
        puzzleSolver = new PuzzleSolver1("src/test/data");
        puzzleSolverExamplesPart1 = new PuzzleSolver1("src/test/examples/part1");
        puzzleSolverExamplesPart2 = new PuzzleSolver1("src/test/examples/part2");
    }

    @Test
    void testPuzzle11() {
        assertEquals(54605, puzzleSolver.puzzle11());
    }
    @Test
    void testPuzzleExample11() {
        assertEquals(142, puzzleSolverExamplesPart1.puzzle11());
    }
    @Test
    void testPuzzle12() {
        assertEquals(55429, puzzleSolver.puzzle12());
    }
    @Test
    void testPuzzleExample12() {
        assertEquals(281, puzzleSolverExamplesPart2.puzzle12());
    }
    @Test
    void testPuzzle21() {
        assertEquals(2810, puzzleSolver.puzzle21());
    }
    @Test
    void testPuzzleExample21() {
        assertEquals(8, puzzleSolverExamplesPart1.puzzle21());
    }
    @Test
    void testPuzzle22() {
        assertEquals(69110, puzzleSolver.puzzle22());
    }
    @Test
    void testPuzzleExample22() {
        assertEquals(2286, puzzleSolverExamplesPart2.puzzle22());
    }
    @Test
    void testPuzzle31() {
        assertEquals(537732, puzzleSolver.puzzle31());
    }
    @Test
    void testPuzzleExample31() {
        assertEquals(4361, puzzleSolverExamplesPart1.puzzle31());
    }
    @Test
    void testPuzzle32() {
        assertEquals(84883664, puzzleSolver.puzzle32());
    }
    @Test
    void testPuzzleExample32() {
        assertEquals(467835, puzzleSolverExamplesPart2.puzzle32());
    }
    @Test
    void testPuzzle41() {
        assertEquals(26346, puzzleSolver.puzzle41());
    }
    @Test
    void testPuzzleExample41() {
        assertEquals(13, puzzleSolverExamplesPart1.puzzle41());
    }
    @Test
    void testPuzzle42() {
        assertEquals(8467762, puzzleSolver.puzzle42());
    }
    @Test
    void testPuzzleExample42() {
        assertEquals(30, puzzleSolverExamplesPart2.puzzle42());
    }
    @Test
    void testPuzzle51() {
        assertEquals(382895070, puzzleSolver.puzzle51());
    }
    @Test
    void testPuzzleExample51() {
        assertEquals(35, puzzleSolverExamplesPart1.puzzle51());
    }
    @Test
    void testPuzzle52() {
        assertEquals(17729182, puzzleSolver.puzzle52());
    }
    @Test
    void testPuzzleExample52() {
        assertEquals(46, puzzleSolverExamplesPart2.puzzle52());
    }
}

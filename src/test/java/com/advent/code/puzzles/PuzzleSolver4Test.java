package com.advent.code.puzzles;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PuzzleSolver4Test {

    private static PuzzleSolver4 puzzleSolver;
    private static PuzzleSolver4 puzzleSolverExamplesPart1;
    private static PuzzleSolver4 puzzleSolverExamplesPart2;

    @BeforeAll
    static void setup() {
        puzzleSolver = new PuzzleSolver4("src/main/resources");
        puzzleSolverExamplesPart1 = new PuzzleSolver4("src/test/examples/part1");
        puzzleSolverExamplesPart2 = new PuzzleSolver4("src/test/examples/part2");
    }

    @Test
    void testPuzzle161() {
        assertEquals(6514, puzzleSolver.puzzle161());
    }
    @Test
    void testPuzzleExample161() {
        assertEquals(46, puzzleSolverExamplesPart1.puzzle161());
    }
    @Test
    void testPuzzle162() {
        assertEquals(8089, puzzleSolver.puzzle162());
    }
    @Test
    void testPuzzleExample162() {
        assertEquals(51, puzzleSolverExamplesPart2.puzzle162());
    }
    @Disabled("Takes to long")
    @Test
    void testPuzzle171() {
        assertEquals(755, puzzleSolver.puzzle171());
    }
    @Test
    void testPuzzleExample171() {
        assertEquals(102, puzzleSolverExamplesPart1.puzzle171());
    }
    @Disabled("Takes to long")
    @Test
    void testPuzzle172() {
        assertEquals(881, puzzleSolver.puzzle172());
    }
    @Test
    void testPuzzleExample172() {
        assertEquals(94, puzzleSolverExamplesPart2.puzzle172());
    }
    @Test
    void testPuzzle181() {
        assertEquals(49061, puzzleSolver.puzzle181());
    }
    @Test
    void testPuzzleExample181() {
        assertEquals(62, puzzleSolverExamplesPart1.puzzle181());
    }
    @Test
    void testPuzzle182() {
        assertEquals(92556825427032L, puzzleSolver.puzzle182());
    }
    @Test
    void testPuzzleExample182() {
        assertEquals(952408144115L, puzzleSolverExamplesPart2.puzzle182());
    }
    @Test
    void testPuzzle191() {
        assertEquals(386787, puzzleSolver.puzzle191());
    }
    @Test
    void testPuzzleExample191() {
        assertEquals(19114, puzzleSolverExamplesPart1.puzzle191());
    }
    @Test
    void testPuzzle192() {
        assertEquals(131029523269531L, puzzleSolver.puzzle192());
    }
    @Test
    void testPuzzleExample192() {
        assertEquals(167409079868000L, puzzleSolverExamplesPart2.puzzle192());
    }
    @Test
    void testPuzzle201() {
        assertEquals(743871576, puzzleSolver.puzzle201());
    }
    @Test
    void testPuzzleExample201() {
        assertEquals(11687500, puzzleSolverExamplesPart1.puzzle201());
    }
    @Test
    void testPuzzle202() {
        assertEquals(244151741342687L, puzzleSolver.puzzle202());
    }
}

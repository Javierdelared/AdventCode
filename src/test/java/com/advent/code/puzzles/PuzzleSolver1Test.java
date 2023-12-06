package com.advent.code.puzzles;

import com.advent.code.utils.LineReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PuzzleSolver1Test {

    private static PuzzleSolver1 puzzleSolver;

    @BeforeAll
    static void setup() {
        LineReader lineReader = new LineReader();
        lineReader.setBasePath("src/test/data");
        puzzleSolver = new PuzzleSolver1(lineReader);
    }

    @Test
    void testPuzzle11() {
        assertEquals(54605, puzzleSolver.puzzle11());
    }

    @Test
    void testPuzzle12() {
        assertEquals(55429, puzzleSolver.puzzle12());
    }

    @Test
    void testPuzzle21() {
        assertEquals(2810, puzzleSolver.puzzle21());
    }

    @Test
    void testPuzzle22() {
        assertEquals(69110, puzzleSolver.puzzle22());
    }

    @Test
    void testPuzzle31() {
        assertEquals(537732, puzzleSolver.puzzle31());
    }

    @Test
    void testPuzzle32() {
        assertEquals(84883664, puzzleSolver.puzzle32());
    }

    @Test
    void testPuzzle41() {
        assertEquals(26346, puzzleSolver.puzzle41());
    }

    @Test
    void testPuzzle42() {
        assertEquals(8467762, puzzleSolver.puzzle42());
    }

    @Test
    void testPuzzle51() {
        assertEquals(382895070, puzzleSolver.puzzle51());
    }

    @Test
    void testPuzzle52() {
        assertEquals(17729182, puzzleSolver.puzzle52());
    }
}

package com.advent.code.puzzles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PuzzleSolver1Test {

    @Test
    void testPuzzle11() {
        assertEquals(54605, PuzzleSolver1.puzzle11());
    }

    @Test
    void testPuzzle12() {
        assertEquals(55429, PuzzleSolver1.puzzle12());
    }

    @Test
    void testPuzzle21() {
        assertEquals(2810, PuzzleSolver1.puzzle21());
    }

    @Test
    void testPuzzle22() {
        assertEquals(69110, PuzzleSolver1.puzzle22());
    }

    @Test
    void testPuzzle31() {
        assertEquals(537732, PuzzleSolver1.puzzle31());
    }

    @Test
    void testPuzzle32() {
        assertEquals(84883664, PuzzleSolver1.puzzle32());
    }

    @Test
    void testPuzzle41() {
        assertEquals(26346, PuzzleSolver1.puzzle41());
    }

    @Test
    void testPuzzle42() {
        assertEquals(8467762, PuzzleSolver1.puzzle42());
    }

    @Test
    void testPuzzle51() {
        assertEquals(382895070, PuzzleSolver1.puzzle51());
    }

    @Test
    void testPuzzle52() {
        assertEquals(17729182, PuzzleSolver1.puzzle52());
    }
}

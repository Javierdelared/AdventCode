package com.advent.code.puzzles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PuzzleSolver2Test {

    @Test
    void testPuzzle61() {
        assertEquals(252000, PuzzleSolver2.puzzle61());
    }

    @Test
    void testPuzzle62() {
        assertEquals(36992486, PuzzleSolver2.puzzle62());
    }
}

package com.advent.code.utils;

import com.advent.code.models.Coordinates;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ParseUtilsTest {

    @Test
    void parseLongNumbers() {
        // Given
        String line = "asdas3d 3242 34g 98635  7 gt 88 !  56,7 33.21 -54 5-4";
        List<Long> expectedResult = List.of(3242L, 98635L, 7L, 88L, -54L);
        // When then
        assertEquals(expectedResult, ParseUtils.parseLongNumbers(line));
    }

    @Test
    void parseLineToBooleanList() {
        // Given
        String line = "#....O#O";
        List<Boolean> expectedResult = List.of(true, false, false, false, false, false, true, false);
        // When then
        assertEquals(expectedResult, ParseUtils.parseLineToBooleanList(line, '#'));
    }

    @Test
    void parseLineToCoordinates() {
        // Given
        String line = "#....O#O";
        List<Coordinates> expectedResult = List.of(new Coordinates(0, 3), new Coordinates(6,3));
        // When then
        assertEquals(expectedResult, ParseUtils.parseLineToCoordinates(line, 3,'#'));
    }
}

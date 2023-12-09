package com.advent.code.utils;

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
}

package com.advent.code.utils;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    @Test
    void getPrimeFactors() {
        Map<Integer, Integer> expectedPrimeFactors = Map.of(79, 1, 293, 1);
        assertEquals(expectedPrimeFactors, MathUtils.getPrimeFactors(23147));
    }

    @Test
    void getLCM() {
        List<Integer> numbers = List.of(23147, 17287, 21389, 13771, 19631, 20803);
        assertEquals(22289513667691L, MathUtils.lcm(numbers));
    }
}
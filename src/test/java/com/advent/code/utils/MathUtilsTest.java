package com.advent.code.utils;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    @Test
    void getPrimeFactors() {
        Map<Integer, Integer> expectedPrimeFactors = Map.of(79, 1, 293, 1);
        assertEquals(expectedPrimeFactors, MathUtils.getPrimeFactors(23147L));
    }

    @Test
    void getLCM() {
        LongStream numbers = LongStream.of(23147L, 17287L, 21389L, 13771L, 19631L, 20803L);
        assertEquals(22289513667691L, MathUtils.lcm(numbers));
    }
}
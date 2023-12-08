package com.advent.code.utils;

import java.util.*;
import java.util.stream.LongStream;

public class MathUtils {

    public static Map<Integer, Integer> getPrimeFactors(long number) {
        Map<Integer, Integer> primeFactorsMap = new HashMap<>();
        for (int factor = 2; factor <= number; factor++) {
            while (number % factor == 0) {
                primeFactorsMap.put(factor, primeFactorsMap.getOrDefault(factor, 0) + 1);
                number /= factor;
            }
        }
        return primeFactorsMap;
    }

    public static long lcm(LongStream numbers) {
        return numbers.reduce(1L, MathUtils::lcm);
    }

    public static long lcm(long n1, long n2) {
        Map<Integer, Integer> pf1 = getPrimeFactors(n1);
        Map<Integer, Integer> pf2 = getPrimeFactors(n2);
        Set<Integer> primeFactorsUnionSet = new HashSet<>(pf1.keySet());
        primeFactorsUnionSet.addAll(pf2.keySet());
        long lcm = 1L;
        for (Integer primeFactor : primeFactorsUnionSet) {
            lcm *= Math.pow(primeFactor,
                    Math.max(pf1.getOrDefault(primeFactor, 0),
                            pf2.getOrDefault(primeFactor, 0)));
        }
        return lcm;
    }
}

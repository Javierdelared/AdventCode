package com.advent.code.utils;

import java.util.*;

public class MathUtils {

    public static Map<Integer, Integer> getPrimeFactors(int number) {
        Map<Integer, Integer> pfMap = new HashMap<>();
        for (int factor = 2; factor <= number; factor++) {
            while (number % factor == 0) {
                pfMap.put(factor, pfMap.getOrDefault(factor, 0) + 1);
                number /= factor;
            }
        }
        return pfMap;
    }
    public static long lcm(List<Integer> numbers) {
        Set<Integer> pfSet = new HashSet<>();
        List<Map<Integer, Integer>> pfList = numbers.stream().map(MathUtils::getPrimeFactors).toList();
        pfList.forEach(pf -> pfSet.addAll(pf.keySet()));
        return pfSet.stream().mapToLong(pf ->
                (long) Math.pow(pf, pfList.stream().mapToInt(pw ->
                        pw.getOrDefault(pf, 0)).max().orElse(0))).reduce(1L, (a, b) -> a * b);
    }

    public static int calculateHash(String s) {
        int hash = 0;
        for (int i = 0; i <s.length(); i++) {
            hash += s.charAt(i);
            hash *= 17;
            hash = hash % 256;
        }
        return hash;
    }
}

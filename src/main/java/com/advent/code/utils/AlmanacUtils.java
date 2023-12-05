package com.advent.code.utils;

import com.advent.code.dto.AlmanacMap;
import com.advent.code.dto.MapRange;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class AlmanacUtils {
    private final static Pattern patternThreeNumbers = Pattern.compile("^([0-9]+) ([0-9]+) ([0-9]+)$");
    private final static Pattern patternMap = Pattern.compile("^([a-z]+)-to-([a-z]+) map:$");
    public static Map<String, AlmanacMap> getAlmanacMaps(List<String> lines) {
        Map<String, AlmanacMap> almanacMaps = new HashMap<>();
        String currentSource = null;
        for (String line : lines) {
            Matcher matcherMap = patternMap.matcher(line);
            if (matcherMap.find()) {
                currentSource = matcherMap.group(1);
                almanacMaps.put(currentSource, new AlmanacMap(currentSource, matcherMap.group(2)));
            } else {
                Matcher matcherThreeNumbers = patternThreeNumbers.matcher(line);
                if (matcherThreeNumbers.find()) {
                    almanacMaps.get(currentSource).addRange(Long.parseLong(matcherThreeNumbers.group(1)),
                            Long.parseLong(matcherThreeNumbers.group(2)), Long.parseLong(matcherThreeNumbers.group(3)));
                }
            }
        }
        return almanacMaps;
    }

    public static List<Long> getSeeds(String line) {
        return Stream.of(line.split(" ")).filter(s -> s.matches("[0-9]+"))
                .map(Long::parseLong).toList();
    }

    public static AlmanacMap getSeedRanges(String line) {
        List<Long> seedRanges = getSeeds(line);
        AlmanacMap seedRangeMap = new AlmanacMap("origin", "seed");
        Long start = null;
        for (int i = 0; i < seedRanges.size(); i++) {
            long n = seedRanges.get(i);
            if (i % 2 == 0) {
                start = n;
            } else {
                seedRangeMap.addRange(start, start, n);
            }
        }
        return seedRangeMap;
    }

    public static List<MapRange> getNewAlmanacMapRanges(List<MapRange> currentMapRanges,
                                                        List<MapRange> almanacMapRanges) {
        List<MapRange> newAlmanacMapRanges = new ArrayList<>();
        for (MapRange currentMapRange : currentMapRanges) {
            List<MapRange> newMapRanges = getNewMapRanges(almanacMapRanges, currentMapRange);
            newMapRanges.addAll(findUnMappedRanges(currentMapRange, newMapRanges));
            newAlmanacMapRanges.addAll(newMapRanges);
        }
        return newAlmanacMapRanges;
    }

    private static List<MapRange> getNewMapRanges(List<MapRange> almanacMapRanges, MapRange currentMapRange) {
        List<MapRange> newMapRanges = new ArrayList<>();
        for (MapRange almanacMapRange : almanacMapRanges) {
            MapRange commonMapRange = currentMapRange.getCommonRange(almanacMapRange);
            if (commonMapRange != null) {
                newMapRanges.add(commonMapRange);
            }
        }
        return newMapRanges;
    }

    private static List<MapRange> findUnMappedRanges(MapRange currentMapRange, List<MapRange> newMapRanges) {
        long firstUnMappedNumber = currentMapRange.getDestinationFirstElement();
        if (newMapRanges.isEmpty()) {
            return List.of(new MapRange(firstUnMappedNumber, firstUnMappedNumber, currentMapRange.getRangeLength()));
        }
        List<MapRange> unMappedRanges = new ArrayList<>();
        newMapRanges.sort(Comparator.comparingLong(MapRange::getSourceFirstElement));
        for (MapRange currentNewMapRange : newMapRanges) {
            if (firstUnMappedNumber < currentNewMapRange.getSourceFirstElement()) {
                long rangeLength = currentNewMapRange.getSourceFirstElement() - firstUnMappedNumber + 1;
                unMappedRanges.add(new MapRange(firstUnMappedNumber, firstUnMappedNumber, rangeLength));
            }
            firstUnMappedNumber = currentNewMapRange.getSourceLastElement() + 1;
        }
        return unMappedRanges;
    }
}

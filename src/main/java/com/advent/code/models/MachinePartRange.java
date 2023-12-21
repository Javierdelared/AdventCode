package com.advent.code.models;
import java.util.HashMap;
import java.util.Map;

public class MachinePartRange {

    public static final int MIN_RANGE = 1;
    public static final int MAX_RANGE = 4001;

    private static final Map<String, Integer> MIN_RANGE_MAP = Map.of("x", MIN_RANGE, "m", MIN_RANGE,
            "a", MIN_RANGE, "s", MIN_RANGE);
    private static final Map<String, Integer> MAX_RANGE_MAP = Map.of("x", MAX_RANGE, "m", MAX_RANGE,
            "a", MAX_RANGE, "s", MAX_RANGE);

    public static final MachinePartRange FULL_RANGE = new MachinePartRange(MIN_RANGE_MAP, MAX_RANGE_MAP);
    public static final MachinePartRange EMPTY_RANGE = new MachinePartRange(MIN_RANGE_MAP, MIN_RANGE_MAP);
    private final Map<String, Integer> minValues;
    private final Map<String, Integer> maxValues;

    public MachinePartRange(Map<String, Integer> minValues, Map<String, Integer> maxValues) {
        this.minValues = minValues;
        this.maxValues = maxValues;
    }

    public static MachinePartRange build(String field, int minRange, int maxRange) {
        Map<String, Integer> minValues = new HashMap<>(MIN_RANGE_MAP);
        Map<String, Integer> maxValues = new HashMap<>(MAX_RANGE_MAP);
        minValues.put(field, minRange);
        maxValues.put(field, maxRange);
        return new MachinePartRange(minValues, maxValues);
    }

    public MachinePartRange combine(MachinePartRange range) {
        Map<String, Integer> newMinValues = new HashMap<>();
        Map<String, Integer> newMaxValues = new HashMap<>();
        minValues.forEach((key, value) -> newMinValues.put(key, Math.max(value, range.minValues.get(key))));
        maxValues.forEach((key, value) -> newMaxValues.put(key, Math.min(value, range.maxValues.get(key))));
        if (minValues.entrySet().stream().allMatch(e -> e.getValue() < maxValues.get(e.getKey()))) {
            return new MachinePartRange(newMinValues, newMaxValues);
        } else {
            return EMPTY_RANGE;
        }
    }

    public MachinePartRange reverse(String field) {
        if (field == null) {
            return EMPTY_RANGE;
        }
        if (minValues.get(field) == MIN_RANGE) {
            Map<String, Integer> newMinValues = new HashMap<>(MIN_RANGE_MAP);
            newMinValues.put(field, maxValues.get(field));
            return new MachinePartRange(newMinValues, MAX_RANGE_MAP);
        }
        Map<String, Integer> newMaxValues = new HashMap<>(MAX_RANGE_MAP);
        newMaxValues.put(field, minValues.get(field));
        return new MachinePartRange(MIN_RANGE_MAP, newMaxValues);
    }

    public long calculateRangeCombinations() {
        long combinations = 1;
        for (Map.Entry<String, Integer> entry : minValues.entrySet()) {
            combinations *= maxValues.get(entry.getKey()) - entry.getValue();
        }
        return combinations;
    }
}

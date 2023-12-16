package com.advent.code.models;

import java.util.HashMap;
import java.util.Map;

public class Box {
    private final Map<Lens, Integer> lensPositions = new HashMap<>();

    private final int pos;

    public Box(int pos){
        this.pos = pos;
    }

    public void addLens(Lens lens) {
        if (lensPositions.containsKey(lens)) {
            int lensPos = lensPositions.get(lens);
            lensPositions.remove(lens);
            lensPositions.put(lens, lensPos);
        } else {
            lensPositions.put(lens, lensPositions.size() + 1);
        }
    }

    public void removeLens(Lens lens) {
        if (lensPositions.containsKey(lens)) {
            int lensPosition = lensPositions.get(lens);
            lensPositions.remove(lens);
            lensPositions.entrySet().stream().filter(e -> e.getValue() > lensPosition).forEach(e ->
                    lensPositions.put(e.getKey(), e.getValue() - 1));
        }
    }

    public int calculateBoxFocusPower() {
        return lensPositions.entrySet().stream().mapToInt(e -> (1 + pos) * e.getValue() * e.getKey().focus()).sum();
    }
}

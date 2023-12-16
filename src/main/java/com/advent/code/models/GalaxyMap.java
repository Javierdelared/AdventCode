package com.advent.code.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GalaxyMap {
    private final List<List<Boolean>> image;
    private final int expansion;
    private List<Coordinates> expandedGalaxyPositions;

    public GalaxyMap(List<List<Boolean>> galaxyPositions, int spaceMultiplier) {
        this.image = galaxyPositions;
        this.expansion = spaceMultiplier - 1;
    }

    public List<Coordinates> getExpandedGalaxyPositions() {
        if (expandedGalaxyPositions == null) {
            expandedGalaxyPositions = calculateGalaxyPositions();
        }
        return expandedGalaxyPositions;
    }

    private List<Coordinates> calculateGalaxyPositions() {
        List<Coordinates> expandedGalaxyPositions = new ArrayList<>();
        int lengthX = image.get(0).size();
        int lengthY = image.size();
        List<Integer> columnIndexWithoutGalaxies = IntStream.range(0, lengthX)
                .filter(x -> isEmptyColumn(x, lengthY)).boxed().toList();
        List<Integer> rowIndexWithoutGalaxies = IntStream.range(0, lengthY)
                .filter(y -> image.get(y).stream().noneMatch(p -> p)).boxed().toList();
        for (int x = 0; x < lengthX ; x++) {
            for (int y = 0; y < lengthY; y++) {
                if (image.get(y).get(x)) {
                    final int column = x;
                    final int row = y;
                    int columnsAdded = (int) columnIndexWithoutGalaxies.stream().filter(c -> c < column).count();
                    int rowsAdded = (int) rowIndexWithoutGalaxies.stream().filter(r -> r < row).count();
                    expandedGalaxyPositions.add(
                            new Coordinates(x + columnsAdded * expansion, y + rowsAdded * expansion));
                }
            }
        }
        return expandedGalaxyPositions;
    }

    private boolean isEmptyColumn(int x, int lengthY) {
        for (int y = 0; y < lengthY; y++) {
            if (image.get(y).get(x)) {
                return false;
            }
        }
        return true;
    }
}

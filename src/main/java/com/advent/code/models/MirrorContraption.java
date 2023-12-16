package com.advent.code.models;

import java.util.*;
import java.util.stream.IntStream;

import static com.advent.code.models.Direction.*;

public class MirrorContraption {

    private final Map<Coordinates, Mirror> mirrorPositions;
    private final Coordinates maxC;
    private Set<Coordinates> energisedPositions = new HashSet<>();
    private Set<Vector> sendLightCache = new HashSet<>();

    public MirrorContraption(Map<Coordinates, Mirror> mirrorPositions, Coordinates maxC) {
        this.mirrorPositions = mirrorPositions;
        this.maxC = maxC;
    }

    public
    void sendLight(Vector v) {
        if (!v.c().isInRange(Coordinates.ORIGIN, maxC) || sendLightCache.contains(v)) {
            return ;
        }
        sendLightCache.add(v);
        energisedPositions.add(v.c());
        if (mirrorPositions.containsKey(v.c())) {
            mirrorPositions.get(v.c()).reflect(v.d()).forEach(newD -> sendLight(v.move(newD)));
        } else {
            sendLight(v.move());
        }
    }

    public Set<Vector> calculateSendLightEdges() {
        Set<Vector> sendLightEdges = new HashSet<>();
        IntStream.range(0, maxC.x()).forEach(x -> {
            sendLightEdges.add(Vector.build(x, 0, SOUTH));
            sendLightEdges.add(Vector.build(x, maxC.y() - 1, NORTH));
        });
        IntStream.range(0, maxC.y()).forEach(y -> {
            sendLightEdges.add(Vector.build(0, y, EAST));
            sendLightEdges.add(Vector.build(maxC.x() - 1, y, WEST));
        });
        return sendLightEdges;
    }

    public int getNumberEnergisedTiles() {
        return energisedPositions.size();
    }

    public void clean() {
        energisedPositions = new HashSet<>();
        sendLightCache = new HashSet<>();
    }
}

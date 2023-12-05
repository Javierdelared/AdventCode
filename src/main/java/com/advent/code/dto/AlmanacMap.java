package com.advent.code.dto;

import java.util.ArrayList;
import java.util.List;

public class AlmanacMap {

    private final String source;
    private final String destination;

    private final List<MapRange> mapRanges = new ArrayList<>();

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public List<MapRange> getMapRanges() {
        return mapRanges;
    }

    public AlmanacMap(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public void addRange(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
        mapRanges.add(new MapRange(destinationRangeStart, sourceRangeStart, rangeLength));
    }

    public long calculateDestination(long source) {
        for (MapRange mapRange : mapRanges) {
            Long destination = mapRange.getDestinationFromSource(source);
            if (destination != null) {
                return destination;
            }
        }
        return source;
    }
}

package com.advent.code.dto;

public class MapRange {

    private final long destinationRangeStart;
    private final long sourceRangeStart;
    private final long rangeLength;

    private final long sourceFirstElement;
    private final long sourceLastElement;
    private final long destinationFirstElement;
    private final long destinationLastElement;

    public long getSourceFirstElement() {
        return sourceFirstElement;
    }

    public long getSourceLastElement() {
        return sourceLastElement;
    }

    public long getDestinationFirstElement() {
        return destinationFirstElement;
    }

    public long getDestinationLastElement() {
        return destinationLastElement;
    }

    public long getDestinationRangeStart() {
        return destinationRangeStart;
    }

    public long getSourceRangeStart() {
        return sourceRangeStart;
    }

    public long getRangeLength() {
        return rangeLength;
    }

    public MapRange(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
        this.destinationRangeStart = destinationRangeStart;
        this.sourceRangeStart = sourceRangeStart;
        this.rangeLength = rangeLength;
        sourceFirstElement = sourceRangeStart;
        sourceLastElement = sourceRangeStart + rangeLength - 1;
        destinationFirstElement = destinationRangeStart;
        destinationLastElement = destinationRangeStart + rangeLength -1;
    }

    public Long getDestinationFromSource(long source) {
        if (sourceFirstElement <= source && source <= sourceLastElement) {
            return destinationFirstElement + (source - sourceFirstElement);
        }
        return null;
    }

    public MapRange getCommonRange(MapRange newMapRange) {
        long sourceFirstElement = newMapRange.getSourceFirstElement();
        long sourceLastElement = newMapRange.getSourceLastElement();
        // Ranges don't have common range.
        if (sourceFirstElement > destinationLastElement ||
                sourceLastElement < destinationFirstElement) {
            return null;
        }
        // This destination range is inside the new source range.
        if (sourceFirstElement <= destinationFirstElement &&
                sourceLastElement >= destinationLastElement) {
            return new MapRange(newMapRange.getDestinationFromSource(destinationFirstElement), destinationFirstElement,
                rangeLength);
        }
        // The new source range is inside this destination range.
        if (sourceFirstElement >= destinationFirstElement &&
                sourceLastElement <= destinationLastElement) {
            return newMapRange;
        }
        // The new source range is partially in this destination range and the new range is shifted to the left.
        if (sourceFirstElement <= destinationFirstElement) {
            return new MapRange(newMapRange.getDestinationFromSource(destinationFirstElement), destinationFirstElement,
                    sourceLastElement - destinationFirstElement + 1);
        }
        // The new source range is partially in this destination range and the new range is shifted to the right.
        return new MapRange(newMapRange.getDestinationFirstElement(), sourceFirstElement,
                destinationLastElement - sourceFirstElement + 1);
    }
}

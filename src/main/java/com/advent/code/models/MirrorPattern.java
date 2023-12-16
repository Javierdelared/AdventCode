package com.advent.code.models;

import java.util.List;
import java.util.stream.IntStream;

public class MirrorPattern {

    private final List<List<Boolean>> pattern;
    private final int maxX;
    private final int maxY;


    public MirrorPattern(List<List<Boolean>> pattern) {
        this.pattern = pattern;
        maxX = pattern.get(0).size();
        maxY = pattern.size();
    }

    public int calculateMirrorPositions() {
        return IntStream.range(1, maxX).filter(this::isVerticalMirror).sum() +
               100 * IntStream.range(1, maxY).filter(this::isHorizontalMirror).sum();
    }

    public int calculateSmudgedMirrorPositions(int smudges) {
        return IntStream.range(1, maxX).filter(i -> isVerticalSmudgedMirror(i, smudges)).sum() +
                100 * IntStream.range(1, maxY).filter(i -> isHorizontalSmudgedMirror(i, smudges)).sum();
    }

    private boolean isVerticalMirror(int x) {
        return isVerticalSmudgedMirror(x, 0);
    }

    private boolean isVerticalSmudgedMirror(int x, int maxSmudges) {
        int leftPos = x - 1;
        int rightPos = x;
        int smudges = 0;
        while (leftPos >= 0 && rightPos < maxX) {
            for (int y = 0; y < maxY; y++) {
                if (pattern.get(y).get(leftPos) != pattern.get(y).get(rightPos)) {
                    smudges++;
                }
                if (smudges > maxSmudges) {
                    return false;
                }
            }
            leftPos--;
            rightPos++;
        }
        return smudges == maxSmudges;
    }

    private boolean isHorizontalMirror(int y) {
        return isHorizontalSmudgedMirror(y, 0);
    }

    private boolean isHorizontalSmudgedMirror(int y, int maxSmudges) {
        int upPos = y - 1;
        int downPos = y;
        int smudges = 0;
        while (upPos >= 0 && downPos < maxY) {
            for (int x = 0; x < maxX; x++) {
                if (pattern.get(upPos).get(x) != pattern.get(downPos).get(x)) {
                    smudges++;
                }
                if (smudges > maxSmudges) {
                    return false;
                }
            }
            upPos--;
            downPos++;
        }
        return smudges == maxSmudges;
    }
}

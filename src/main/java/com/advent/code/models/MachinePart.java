package com.advent.code.models;

import com.advent.code.exception.ServiceException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record MachinePart(int x, int m, int a, int s) {

    private final static Pattern patternMachinePart = Pattern.compile("^\\{x=([0-9]*),m=([0-9]*),a=([0-9]*),s=([0-9]*)}$");

    public int calculateTotalRating() {
        return x + m + a + s;
    }
    public static MachinePart parseMachinePart(String line) {
        Matcher matcher = patternMachinePart.matcher(line);
        if (matcher.find()) {
            return new MachinePart(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        }
        throw new ServiceException("Machine part not parsed. Line: " + line);
    }
}

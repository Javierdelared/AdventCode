package com.advent.code.models;

import com.advent.code.exception.ServiceException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirectionNode {

    private final static Pattern pattern = Pattern.compile("^(.{3}) = \\((.{3}), (.{3})\\)");
    private final String origin;
    private final String left;
    private final String right;

    public String getOrigin() {
        return origin;
    }

    public DirectionNode(String origin, String left, String right) {
        this.origin = origin;
        this.left = left;
        this.right = right;
    }

    public String getNewPosition(char c) {
        return switch (c) {
            case 'R' -> right;
            case 'L' -> left;
            default -> throw new ServiceException("Direction not found");
        };
    }

    public static DirectionNode parseDirectionNode(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return new DirectionNode(matcher.group(1), matcher.group(2), matcher.group(3));
        } else {
            return null;
        }
    }
}

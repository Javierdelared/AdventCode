package com.advent.code.models;

import com.advent.code.exception.ServiceException;

public enum Group {
    LEFT_HAND, RIGHT_HAND, LIMIT;

    public Group reverse() {
        return switch (this) {
            case LEFT_HAND -> RIGHT_HAND;
            case RIGHT_HAND -> LEFT_HAND;
            default -> throw new ServiceException("Cannot reverse group");
        };
    }
}

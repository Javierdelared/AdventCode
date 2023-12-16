package com.advent.code.models;

import java.util.Objects;

public record Lens(String label, Integer focus) {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lens lens = (Lens) o;
        return Objects.equals(label, lens.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}

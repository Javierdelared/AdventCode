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
        return calculateHash(label);
    }

    public static int calculateHash(String label) {
        int hash = 0;
        for (int i = 0; i <label.length(); i++) {
            hash += label.charAt(i);
            hash *= 17;
            hash = hash % 256;
        }
        return hash;
    }

    public static Lens parseLens(String step) {
        String[] lensArr = step.split("[=\\-]");
        if (lensArr.length > 1) {
            return new Lens(lensArr[0], Integer.parseInt(lensArr[1]));
        } else {
            return new Lens(lensArr[0], null);
        }
    }
}

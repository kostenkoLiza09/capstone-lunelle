package org.example.backend.model.enums;

import lombok.Getter;

@Getter
public enum Selection {

    MEN("Men"),
    WOMEN("Women"),
    UNISEX("Unisex");

    private final String value;

    Selection(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

package org.example.backend.model.enums;

import lombok.Getter;

@Getter
public enum Selection {

    MAN("Man"),
    WOMAN("Woman"),
    UNISEX("Unisex");

    private final String value;

    Selection(String value) {
        this.value = value;
    }
}

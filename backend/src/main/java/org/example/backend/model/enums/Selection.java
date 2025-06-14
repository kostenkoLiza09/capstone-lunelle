package org.example.backend.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
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

    @JsonValue
    public String getSelection() {
        return value;
    }
}

package org.example.backend.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Volume {
    ML20("20"),
    ML30("30"),
    ML50("50"),
    ML90("90"),
    ML100("100"),
    ML150("150"),
    ML200("200");

    Volume(String value) {
        this.value = value;
    }

    private final String value;

    @JsonValue
    public String getVolume() {
        return value;
    }
}

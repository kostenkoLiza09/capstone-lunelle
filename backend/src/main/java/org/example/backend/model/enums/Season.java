package org.example.backend.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Season {

    SUMMER("Summer"),
    WINTER("Winter"),
    AUTUMN("Autumn"),
    SPRING("Spring"),
    ALL("All seasons");

    Season(String value) {
        this.value = value;
    }

    private final String value;

    @JsonValue
    public String getFamilyName() {
        return value;
    }

}

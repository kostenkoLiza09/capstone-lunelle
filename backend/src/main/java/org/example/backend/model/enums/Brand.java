package org.example.backend.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum Brand {
    BYREDO("Byredo"),
    KILIAN("Kilian"),
    INITIO("Initio"),
    GUCCI("Gucci"),
    DIOR("Dior"),
    PRADA("Prada"),
    CHANEL("Chanel"),
    VERSACE("Versace"),
    DOLCEGABBANA("Dolce & Gabbana"),
    ARMANI("Armani"),
    ZARA("Zara"),
    CALVINKLEIN("Calvin Klein");

    private final String value;

    Brand(String value) {
        this.value = value;
    }

    @JsonValue
    public String getNote() {
        return value;
    }
}


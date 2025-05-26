package org.example.backend.model.enums;

import lombok.Getter;

@Getter
public enum ParfumeFamily {

    FLORAL("Floral"),
    WOODY("Woody"),
    ORIENTAL("Oriental"),
    FRESH("Fresh"),
    CITRUS("Citrus"),
    CHYPRE("Chypre"),
    GOURMAND("Gourmand"),
    AROMATIC("Aromatic"),
    MUSKY("Musky"),
    FRUITY("Fruity");

    private final String familyName;

    ParfumeFamily(String familyName) {
        this.familyName = familyName;
    }
}

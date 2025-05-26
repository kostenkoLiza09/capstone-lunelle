package org.example.backend.model.enums;

import lombok.Getter;

@Getter
public enum Notes {

    BERGAMOT("Bergamot"),
    LEMON("Lemon"),
    ROSE("Rose"),
    JASMINE("Jasmine"),
    VANILLA("Vanilla"),
    SANDALWOOD("Sandalwood"),
    CEDARWOOD("Cedarwood"),
    MUSK("Musk"),
    PATCHOULI("Patchouli"),
    AMBER("Amber"),
    PEACH("Peach"),
    BLACKCURRANT("Blackcurrant"),
    COFFEE("Coffee"),
    TONKA_BEAN("Tonka Bean"),
    LAVENDER("Lavender"),
    OUD("Oud"),
    PEPPER("Pepper"),
    AQUATIC("Aquatic");

    private final String note;

    Notes(String note) {
        this.note = note;
    }
}
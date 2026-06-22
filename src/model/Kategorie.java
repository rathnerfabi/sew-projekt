package model;

public enum Kategorie {
    GEMISCHT("Gemischt"),
    ALLGEMEIN("Allgemeinwissen"),
    GEOGRAFIE("Geografie"),
    GESCHICHTE("Geschichte"),
    NATURWISSENSCHAFT("Naturwissenschaft"),
    KULTUR("Kultur");

    private final String anzeigeName;

    Kategorie(String anzeigeName) {
        this.anzeigeName = anzeigeName;
    }

    public String getAnzeigeName() {
        return anzeigeName;
    }
}
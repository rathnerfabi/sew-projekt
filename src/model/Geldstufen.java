package model;

public class Geldstufen {

    public static final int[] BETRAEGE = {
            50, 100, 200, 300, 500,
            1000, 2000, 4000, 8000, 16000,
            32000, 64000, 125000, 500000, 1000000
    };

    public static int getAnzahl() {
        return BETRAEGE.length;
    }

    public static int getBetrag(int frageIndex) {
        return BETRAEGE[frageIndex];
    }

    public static int gewinnBeiAufgeben(int frageIndex) {
        if (frageIndex == 0) {
            return 0;
        }
        return BETRAEGE[frageIndex - 1];
    }
}
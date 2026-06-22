package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frage {
    private String text;
    private List<String> antworten;
    private String richtigeAntwort;
    private Kategorie kategorie;

    public Frage(String text, List<String> antworten, int richtigerIndex, Kategorie kategorie) {
        this.text = text;
        this.antworten = new ArrayList<>(antworten);
        this.richtigeAntwort = antworten.get(richtigerIndex);
        this.kategorie = kategorie;
    }

    // - Antworten durchmischen
    public void mischeAntworten() {
        Collections.shuffle(antworten);
    }

    public boolean istRichtig(int gewaehlterIndex) {
        return antworten.get(gewaehlterIndex).equals(richtigeAntwort);
    }

    public String getText() {
        return text;
    }

    public List<String> getAntworten() {
        return antworten;
    }

    public String getRichtigeAntwort() {
        return richtigeAntwort;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }
}
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frage {
    private String text;
    private List<String> antworten;
    private String richtigeAntwort;

    public Frage(String text, List<String> antworten, int richtigerIndex) {
        this.text = text;
        this.antworten = new ArrayList<>(antworten); // eigene Liste, damit wir mischen duerfen
        this.richtigeAntwort = antworten.get(richtigerIndex);
    }

    // Antworten durchmischen
    public void mischeAntworten() {
        Collections.shuffle(antworten);
    }

    // Prüfe, ob richtige Antwort gewählt
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
}
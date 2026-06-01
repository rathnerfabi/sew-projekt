package model;

import java.util.List;

public class Frage {
    private String text;
    private List<String> antworten;
    private int richtigerIndex;

    public Frage(String text, List<String> antworten, int richtigerIndex) {
        this.text = text;
        this.antworten = antworten;
        this.richtigerIndex = richtigerIndex;
    }

    public boolean istRichtig(int gewaehlterIndex) {
        return gewaehlterIndex == richtigerIndex;
    }

    public String getText() {
        return text;
    }

    public List<String> getAntworten() {
        return antworten;
    }
}

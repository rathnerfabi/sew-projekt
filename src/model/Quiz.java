package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quiz {
    private List<Frage> fragen;
    private int aktuellerIndex;
    private int punkte;

    public Quiz() {
        fragen = new ArrayList<>();
        aktuellerIndex = 0;
        punkte = 0;
        fragenAnlegen();
        Collections.shuffle(fragen); // Reihenfolge zufällig
        for (Frage f : fragen) {
            f.mischeAntworten();     // Antworten innerhalb der Frage mischen
        }
    }

    // Beispielfragen
    private void fragenAnlegen() {
        fragen.add(new Frage("Was ist die Hauptstadt von Österreich?",
                Arrays.asList("Wien", "Berlin", "Madrid", "Paris"), 0));
        fragen.add(new Frage("Wie viel ist 7 * 8?",
                Arrays.asList("54", "56", "58", "64"), 1));
        fragen.add(new Frage("Welche davon ist eine Programmiersprache?",
                Arrays.asList("HTML", "CSS", "Java", "HTTP"), 2));
        fragen.add(new Frage("Wie viele Bits hat ein Byte?",
                Arrays.asList("4", "8", "16", "32"), 1));
        fragen.add(new Frage("Welches Land hat die meisten Einwohner?",
                Arrays.asList("USA", "Indien", "China", "Russland"), 1));
        fragen.add(new Frage("Welches Element hat das Zeichen Au?",
                Arrays.asList("Silber", "Gold", "Kupfer", "Aluminium"), 1));
        fragen.add(new Frage("In welchem Jahr endete der 2. Weltkrieg?",
                Arrays.asList("1943", "1944", "1945", "1946"), 2));
        fragen.add(new Frage("Wer schrieb Faust?",
                Arrays.asList("Schiller", "Goethe", "Kafka", "Brecht"), 1));
    }

    public Frage getAktuelleFrage() {
        return fragen.get(aktuellerIndex);
    }

    // Antwort prüfen, bei richtig Punkte erhöhen
    public boolean beantworten(int gewaehlterIndex) {
        boolean richtig = getAktuelleFrage().istRichtig(gewaehlterIndex);
        if (richtig) {
            punkte++;
        }
        return richtig;
    }

    public boolean hatNaechsteFrage() {
        return aktuellerIndex < fragen.size() - 1;
    }

    public void naechsteFrage() {
        aktuellerIndex++;
    }

    public int getFrageNummer() {
        return aktuellerIndex + 1;
    }

    public int getAnzahlFragen() {
        return fragen.size();
    }

    public int getPunkte() {
        return punkte;
    }
}
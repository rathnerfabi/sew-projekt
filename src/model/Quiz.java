package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quiz {
    private List<Frage> pool;
    private List<Frage> aktuelleFragen;
    private int aktuellerIndex;
    private int gewinn;
    private Spielstatus status;
    private Kategorie gewaehlteKategorie;

    public Quiz(Kategorie kategorie) {
        this.gewaehlteKategorie = kategorie;
        pool = new ArrayList<>();
        fragenAnlegen();

        // Pool nach Kategorie filtern
        aktuelleFragen = new ArrayList<>();
        if (kategorie == Kategorie.GEMISCHT) {
            aktuelleFragen.addAll(pool);
        } else {
            for (Frage f : pool) {
                if (f.getKategorie() == kategorie) {
                    aktuelleFragen.add(f);
                }
            }
        }

        // 15 Fragen zufällig ziehen
        Collections.shuffle(aktuelleFragen);
        int anzahl = Geldstufen.getAnzahl();
        if (aktuelleFragen.size() > anzahl) {
            aktuelleFragen = new ArrayList<>(aktuelleFragen.subList(0, anzahl));
        }

        for (Frage f : aktuelleFragen) {
            f.mischeAntworten();
        }

        aktuellerIndex = 0;
        gewinn = 0;
        status = Spielstatus.LAUFT;
    }

    private void fragenAnlegen() {
        // === GEOGRAFIE ===
        pool.add(new Frage("Was ist die Hauptstadt von Österreich?",
                Arrays.asList("Wien", "Berlin", "Madrid", "Paris"), 0, Kategorie.GEOGRAFIE));
        pool.add(new Frage("Welcher ist der höchste Berg der Welt?",
                Arrays.asList("K2", "Mont Blanc", "Mount Everest", "Kilimandscharo"), 2, Kategorie.GEOGRAFIE));

        // === GESCHICHTE ===
        pool.add(new Frage("In welchem Jahr endete der 2. Weltkrieg?",
                Arrays.asList("1943", "1944", "1945", "1946"), 2, Kategorie.GESCHICHTE));
        pool.add(new Frage("Wann fiel die Berliner Mauer?",
                Arrays.asList("1987", "1989", "1990", "1991"), 1, Kategorie.GESCHICHTE));

        // === NATURWISSENSCHAFT ===
        pool.add(new Frage("Wie viel ist 7 * 8?",
                Arrays.asList("54", "56", "58", "64"), 1, Kategorie.NATURWISSENSCHAFT));
        pool.add(new Frage("Welches Element hat das Zeichen Au?",
                Arrays.asList("Silber", "Gold", "Kupfer", "Aluminium"), 1, Kategorie.NATURWISSENSCHAFT));

        // === KULTUR ===
        pool.add(new Frage("Wer schrieb Faust?",
                Arrays.asList("Schiller", "Goethe", "Kafka", "Brecht"), 1, Kategorie.KULTUR));
        pool.add(new Frage("Wer malte die Mona Lisa?",
                Arrays.asList("Michelangelo", "Leonardo da Vinci", "Raffael", "Van Gogh"), 1, Kategorie.KULTUR));

        // === ALLGEMEINWISSEN ===
        pool.add(new Frage("Wie viele Bits hat ein Byte?",
                Arrays.asList("4", "8", "16", "32"), 1, Kategorie.ALLGEMEIN));
        pool.add(new Frage("Wie viele Spieler hat eine Fußballmannschaft am Feld?",
                Arrays.asList("9", "10", "11", "12"), 2, Kategorie.ALLGEMEIN));
    }

    public Frage getAktuelleFrage() {
        return aktuelleFragen.get(aktuellerIndex);
    }

    public boolean beantworten(int gewaehlterIndex) {
        boolean richtig = getAktuelleFrage().istRichtig(gewaehlterIndex);
        if (richtig) {
            gewinn = Geldstufen.getBetrag(aktuellerIndex);
            if (aktuellerIndex == aktuelleFragen.size() - 1) {
                status = Spielstatus.GEWONNEN;
            }
        } else {
            gewinn = 0;
            status = Spielstatus.FALSCH;
        }
        return richtig;
    }

    public void aufgeben() {
        gewinn = Geldstufen.gewinnBeiAufgeben(aktuellerIndex);
        status = Spielstatus.AUFGEGEBEN;
    }

    public void naechsteFrage() {
        aktuellerIndex++;
    }

    public boolean istVorbei() {
        return status != Spielstatus.LAUFT;
    }

    public int getGewinn() {
        return gewinn;
    }

    public int getFrageNummer() {
        return aktuellerIndex + 1;
    }

    public int getAnzahlFragen() {
        return aktuelleFragen.size();
    }

    public int getAktuellerIndex() {
        return aktuellerIndex;
    }

    public Spielstatus getStatus() {
        return status;
    }

    public Kategorie getGewaehlteKategorie() {
        return gewaehlteKategorie;
    }
}
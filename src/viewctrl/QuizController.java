package viewctrl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Frage;
import model.Geldstufen;
import model.Kategorie;
import model.Quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizController {
    @FXML
    private Label lblZaehler;

    @FXML
    private Label lblKategorie;

    @FXML
    private Label lblFrage;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Label lblFeedback;

    @FXML
    private Button btnWeiter;

    @FXML
    private Button btnAufgeben;

    @FXML
    private VBox boxGeldstufen;

    private Quiz quiz;
    private List<Button> antwortButtons;
    private List<Label> geldstufenLabels;
    private int ausgewaehltIndex = -1;
    private boolean wartetAufWeiter = false;

    @FXML
    private void initialize() {
        Kategorie kat = ViewWechsler.gewaehlteKategorie;
        if (kat == null) {
            kat = Kategorie.GEMISCHT;
        }
        quiz = new Quiz(kat);
        lblKategorie.setText("Kategorie: " + kat.getAnzeigeName());

        antwortButtons = Arrays.asList(btn1, btn2, btn3, btn4);
        geldstufenSidebarBauen();
        zeigeFrage();
    }

    private void geldstufenSidebarBauen() {
        boxGeldstufen.getChildren().clear();
        geldstufenLabels = new ArrayList<>();
        for (int i = 0; i < Geldstufen.getAnzahl(); i++) {
            geldstufenLabels.add(null);
        }
        for (int i = Geldstufen.getAnzahl() - 1; i >= 0; i--) {
            Label l = new Label((i + 1) + ".  " + formatBetrag(Geldstufen.getBetrag(i)) + " EUR");
            geldstufenLabels.set(i, l);
            boxGeldstufen.getChildren().add(l);
        }
        markiereAktuelleStufe();
    }

    private void markiereAktuelleStufe() {
        for (int i = 0; i < geldstufenLabels.size(); i++) {
            Label l = geldstufenLabels.get(i);
            String style = "-fx-padding: 3 6 3 6;";
            if (i == quiz.getAktuellerIndex() && !quiz.istVorbei()) {
                style += "-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-weight: bold;";
            }
            l.setStyle(style);
        }
    }

    private void zeigeFrage() {
        Frage f = quiz.getAktuelleFrage();
        lblZaehler.setText("Frage " + quiz.getFrageNummer() + "/" + quiz.getAnzahlFragen()
                + "  -  Spielen um " + formatBetrag(Geldstufen.getBetrag(quiz.getAktuellerIndex())) + " EUR");
        lblFrage.setText(f.getText());

        for (int i = 0; i < antwortButtons.size(); i++) {
            Button b = antwortButtons.get(i);
            b.setText(f.getAntworten().get(i));
            b.setDisable(false);
            b.setStyle("");
        }

        lblFeedback.setText("");
        btnWeiter.setDisable(true);
        btnAufgeben.setDisable(false);
        ausgewaehltIndex = -1;
        wartetAufWeiter = false;
        markiereAktuelleStufe();
    }

    @FXML
    private void doAntwort1() { antwortGeklickt(0); }

    @FXML
    private void doAntwort2() { antwortGeklickt(1); }

    @FXML
    private void doAntwort3() { antwortGeklickt(2); }

    @FXML
    private void doAntwort4() { antwortGeklickt(3); }

    private void antwortGeklickt(int index) {
        if (wartetAufWeiter) {
            return;
        }
        if (ausgewaehltIndex == index) {
            bestaetigeAntwort(index);
        } else {
            ausgewaehltIndex = index;
            for (int i = 0; i < antwortButtons.size(); i++) {
                if (i == index) {
                    antwortButtons.get(i).setStyle("-fx-background-color: #ff9800; -fx-text-fill: white; -fx-font-weight: bold;");
                } else {
                    antwortButtons.get(i).setStyle("");
                }
            }
            lblFeedback.setText("Loggst du dich fest? Nochmal klicken zum Bestätigen.");
            lblFeedback.setStyle("-fx-font-size: 14px; -fx-text-fill: #ff9800;");
        }
    }

    private void bestaetigeAntwort(int index) {
        Frage f = quiz.getAktuelleFrage();
        int richtigerIdx = f.getAntworten().indexOf(f.getRichtigeAntwort());
        boolean richtig = quiz.beantworten(index);

        antwortButtons.get(index).setStyle(
                richtig
                        ? "-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-weight: bold;"
                        : "-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold;");
        if (!richtig) {
            antwortButtons.get(richtigerIdx).setStyle(
                    "-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-weight: bold;");
        }

        for (Button b : antwortButtons) {
            b.setDisable(true);
        }
        btnAufgeben.setDisable(true);

        if (richtig) {
            lblFeedback.setText("Richtig! Du hast " + formatBetrag(quiz.getGewinn()) + " EUR.");
            lblFeedback.setStyle("-fx-font-size: 14px; -fx-text-fill: #4caf50; -fx-font-weight: bold;");
        } else {
            lblFeedback.setText("Leider falsch. Richtig wäre: " + f.getRichtigeAntwort()
                    + "\nDu gehst leider mit leeren Händen nach Hause.");
            lblFeedback.setStyle("-fx-font-size: 14px; -fx-text-fill: #d32f2f; -fx-font-weight: bold;");
        }

        btnWeiter.setDisable(false);
        if (quiz.istVorbei()) {
            btnWeiter.setText("Ergebnis ansehen");
        } else {
            btnWeiter.setText("Weiter");
        }
        wartetAufWeiter = true;
    }

    @FXML
    private void doWeiter() {
        if (quiz.istVorbei()) {
            zurErgebnisansicht();
        } else {
            quiz.naechsteFrage();
            zeigeFrage();
        }
    }

    @FXML
    private void doAufgeben() {
        if (wartetAufWeiter) {
            return;
        }
        quiz.aufgeben();
        zurErgebnisansicht();
    }

    private void zurErgebnisansicht() {
        ViewWechsler.letzterGewinn = quiz.getGewinn();
        ViewWechsler.letzterStatus = quiz.getStatus();
        ViewWechsler.letzteKategorie = quiz.getGewaehlteKategorie();
        ViewWechsler.wechsleZu("ergebnis.fxml");
    }

    private String formatBetrag(int betrag) {
        return String.format("%,d", betrag);
    }
}
package viewctrl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Frage;
import model.Quiz;

public class QuizController {

    @FXML
    private Label lblZaehler;

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

    private Quiz quiz = new Quiz();

    @FXML
    private void initialize() {
        zeigeFrage();
    }

    // Aktuelle Frage und Antworten in die GUI schreiben
    private void zeigeFrage() {
        Frage f = quiz.getAktuelleFrage();
        lblZaehler.setText("Frage " + quiz.getFrageNummer() + "/" + quiz.getAnzahlFragen());
        lblFrage.setText(f.getText());
        btn1.setText(f.getAntworten().get(0));
        btn2.setText(f.getAntworten().get(1));
        btn3.setText(f.getAntworten().get(2));
        btn4.setText(f.getAntworten().get(3));

        // Buttons wieder freigeben
        btn1.setDisable(false);
        btn2.setDisable(false);
        btn3.setDisable(false);
        btn4.setDisable(false);

        lblFeedback.setText("");
        btnWeiter.setDisable(true); // erst nach dem Antworten freigeben
    }

    @FXML
    private void doAntwort1() {
        pruefe(0);
    }

    @FXML
    private void doAntwort2() {
        pruefe(1);
    }

    @FXML
    private void doAntwort3() {
        pruefe(2);
    }

    @FXML
    private void doAntwort4() {
        pruefe(3);
    }

    // Prueft die gewaehlte Antwort, gibt Feedback, sperrt die Buttons
    private void pruefe(int index) {
        boolean richtig = quiz.beantworten(index);
        if (richtig) {
            lblFeedback.setText("Richtig!");
        } else {
            lblFeedback.setText("Leider falsch. Richtig wäre: " + quiz.getAktuelleFrage().getRichtigeAntwort());
        }

        // Antwort-Buttons sperren, damit man nicht zweimal klickt
        btn1.setDisable(true);
        btn2.setDisable(true);
        btn3.setDisable(true);
        btn4.setDisable(true);
        btnWeiter.setDisable(false);
    }

    @FXML
    private void doWeiter() {
        if (quiz.hatNaechsteFrage()) {
            quiz.naechsteFrage();
            zeigeFrage();
        } else {
            // Quiz fertig: Ergebnis merken, zur Ergebnis-Ansicht wechseln
            ViewWechsler.letztePunkte = quiz.getPunkte();
            ViewWechsler.letzteGesamt = quiz.getAnzahlFragen();
            ViewWechsler.wechsleZu("ergebnis.fxml");
        }
    }
}
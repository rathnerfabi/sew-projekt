package viewctrl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Frage;

import java.util.Arrays;

public class QuizController {
    @FXML private Label lblFrage;
    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btn3;
    @FXML private Label lblFeedback;

    // erstmal nur eine Frage zum Testen
    private Frage frage = new Frage(
            "Was ist die Hauptstadt von Österreich?",
            Arrays.asList("Berlin", "Wien", "Madrid"),
            1);

    @FXML
    private void initialize() {
        zeigeFrage();
    }

    // Frage und Antworten in GUI schreiben
    private void zeigeFrage() {
        lblFrage.setText(frage.getText());
        btn1.setText(frage.getAntworten().get(0));
        btn2.setText(frage.getAntworten().get(1));
        btn3.setText(frage.getAntworten().get(2));
        lblFeedback.setText("");
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

    private void pruefe(int index) {
        if (frage.istRichtig(index)) {
            lblFeedback.setText("Richtig!");
        } else {
            lblFeedback.setText("Leider falsch.");
        }
    }
}
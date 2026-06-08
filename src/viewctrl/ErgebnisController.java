package viewctrl;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ErgebnisController {

    @FXML
    private Label lblErgebnis;

    @FXML
    private void initialize() {
        int punkte = ViewWechsler.letztePunkte;
        int gesamt = ViewWechsler.letzteGesamt;
        lblErgebnis.setText("Du hast " + punkte + " von " + gesamt + " Fragen richtig.");
    }

    @FXML
    private void doZurueck() {
        ViewWechsler.wechsleZu("menue.fxml");
    }
}
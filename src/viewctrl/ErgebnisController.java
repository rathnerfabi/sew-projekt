package viewctrl;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Kategorie;
import model.Spielstatus;

import java.util.Locale;

public class ErgebnisController {

    @FXML
    private Label lblTitel;

    @FXML
    private Label lblGewinn;

    @FXML
    private Label lblNachricht;

    @FXML
    private Label lblKategorie;

    @FXML
    private void initialize() {
        int gewinn = ViewWechsler.letzterGewinn;
        Spielstatus status = ViewWechsler.letzterStatus;
        Kategorie kategorie = ViewWechsler.letzteKategorie;

        lblGewinn.setText(formatBetrag(gewinn) + " EUR");
        if (kategorie != null) {
            lblKategorie.setText("Kategorie: " + kategorie.getAnzeigeName());
        }

        if (status == Spielstatus.GEWONNEN) {
            lblTitel.setText("Gewonnen!");
            lblTitel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #4caf50;");
            lblNachricht.setText("Du hast alle 15 Fragen richtig beantwortet und die Million geknackt!");
        } else if (status == Spielstatus.AUFGEGEBEN) {
            lblTitel.setText("Aufgegeben");
            lblTitel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #ff9800;");
            lblNachricht.setText("Du hast rechtzeitig aufgehört.");
        } else if (status == Spielstatus.FALSCH) {
            lblTitel.setText("Leider falsch");
            lblTitel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #d32f2f;");
            lblNachricht.setText("Du gehst leider mit leeren Händen nach Hause.");
        }
    }

    @FXML
    private void doZurueck() {
        ViewWechsler.wechsleZu("menue.fxml");
    }

    private String formatBetrag(int betrag) {
        return String.format(Locale.GERMAN, "%,d", betrag);
    }
}
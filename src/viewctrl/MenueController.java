package viewctrl;

import javafx.fxml.FXML;
import model.Kategorie;

public class MenueController {

    @FXML
    private void doGemischt() {
        starte(Kategorie.GEMISCHT);
    }

    @FXML
    private void doAllgemein() {
        starte(Kategorie.ALLGEMEIN);
    }

    @FXML
    private void doGeografie() {
        starte(Kategorie.GEOGRAFIE);
    }

    @FXML
    private void doGeschichte() {
        starte(Kategorie.GESCHICHTE);
    }

    @FXML
    private void doNaturwissenschaft() {
        starte(Kategorie.NATURWISSENSCHAFT);
    }

    @FXML
    private void doKultur() {
        starte(Kategorie.KULTUR);
    }

    private void starte(Kategorie kategorie) {
        ViewWechsler.gewaehlteKategorie = kategorie;
        ViewWechsler.wechsleZu("quiz.fxml");
    }

    @FXML
    private void doBeenden() {
        System.exit(0);
    }
}
package viewctrl;

import javafx.fxml.FXML;

public class MenueController {

    @FXML
    private void doSpielen() {
        ViewWechsler.wechsleZu("quiz.fxml");
    }

    @FXML
    private void doBeenden() {
        System.exit(0);
    }
}
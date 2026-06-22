package viewctrl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Kategorie;
import model.Spielstatus;

public class ViewWechsler {
    private static Stage stage;

    // Vom Menü zur Quiz-Ansicht: welche Kategorie wurde gewählt
    public static Kategorie gewaehlteKategorie;

    // Vom Quiz zur Ergebnis-Ansicht
    public static int letzterGewinn;
    public static Spielstatus letzterStatus;
    public static Kategorie letzteKategorie;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void wechsleZu(String fxmlDatei) {
        try {
            Parent root = FXMLLoader.load(ViewWechsler.class.getResource("/viewctrl/" + fxmlDatei));
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
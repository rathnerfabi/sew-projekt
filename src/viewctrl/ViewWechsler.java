package viewctrl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewWechsler {
    private static Stage stage;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void wechsleZu(String fxmlDatei) {
        try {
            Parent root = FXMLLoader.load(
                    ViewWechsler.class.getResource("/viewctrl/" + fxmlDatei));
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
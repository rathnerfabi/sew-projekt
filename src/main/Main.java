package main;

import javafx.application.Application;
import javafx.stage.Stage;
import viewctrl.ViewWechsler;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Quiz");
        ViewWechsler.setStage(primaryStage);
        ViewWechsler.wechsleZu("quiz.fxml");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package com.example.xrestnul;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {

    @Override
    public void start(Stage primaryStage) {
        GameLogic gameLogic = new GameLogic();
        GameUI gameUI = new GameUI(primaryStage, gameLogic);
        gameUI.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
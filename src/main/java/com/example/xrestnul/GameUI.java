package com.example.xrestnul;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameUI {
    private final GameLogic gameLogic;
    private final Stage primaryStage;
    private final Button[][] buttons = new Button[3][3];

    public GameUI(Stage primaryStage, GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.primaryStage = primaryStage;
    }

    public void start() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        GridPane gridPane = createGameBoard();
        root.getChildren().add(gridPane);

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> startGame());
        root.getChildren().add(startButton);

        Scene scene = new Scene(root, 300, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.show();
    }

    private GridPane createGameBoard() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setPrefSize(100, 100);
                button.setOnAction(e -> {
                    if (!gameLogic.isGameOver()) {
                        makeMove(button);
                        if (gameLogic.checkWin(buttons)) {
                            showWinMessage();
                            gameLogic.endGame();
                        } else if (gameLogic.checkTie(buttons)) {
                            showTieMessage();
                            gameLogic.endGame();
                        } else {
                            gameLogic.switchPlayer();
                            if (gameLogic.getCurrentPlayer() == 'O') {
                                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                                pause.play();
                            }
                        }
                    }
                });
                buttons[row][col] = button;
                gridPane.add(button, col, row);
            }
        }
        return gridPane;
    }

    private void makeMove(Button button) {
        gameLogic.makeMove(button);
    }


    private void startGame() {
        gameLogic.startGame(buttons);
    }

    private void showWinMessage() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Win");
        alert.setHeaderText(null);
        alert.setContentText("Player " + gameLogic.getCurrentPlayer() + " wins!");
        alert.showAndWait();
    }

    private void showTieMessage() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setHeaderText(null);
        alert.setContentText("It's a tie!");
        alert.showAndWait();
    }
}
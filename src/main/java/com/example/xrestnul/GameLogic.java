package com.example.xrestnul;

import javafx.scene.control.Button;

public class GameLogic {
    private char currentPlayer = 'X';
    private boolean isGameOver = false;

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void makeMove(Button button) {
        button.setText(Character.toString(currentPlayer));
        button.setDisable(true);
    }


    public boolean checkWin(Button[][] buttons) {
        String[][] board = new String[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = buttons[row][col].getText();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].isEmpty()) {
                return true; // Перевірка по горизонталі
            }
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].isEmpty()) {
                return true; // Перевірка по вертикалі
            }
        }

        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].isEmpty()) {
            return true; // Перевірка по головній діагоналі
        }
        return board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].isEmpty(); // Перевірка по побічній діагоналі
    }

    public boolean checkTie(Button[][] buttons) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void startGame(Button[][] buttons) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setDisable(false);
            }
        }
        currentPlayer = 'X';
        isGameOver = false;
    }

    public void endGame() {
        isGameOver = true;
    }
}

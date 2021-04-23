package tictactoe.GameLogic;

import java.util.Random;

public interface AI {

    int[] getTurn(char[][] gameBoard, char currentPlayer);

    String getName();

    default int[] randomTurn(char[][] gameBoard) {
        Random rand = new Random();
        int[] turn = new int[2];
        do {
            turn[0] = rand.nextInt(3);
            turn[1] = rand.nextInt(3);
        } while (gameBoard[turn[0]][turn[1]] != '_');

        return turn;
    }

    default int evaluateBoard(char[][] gameBoard, char currentPlayer) {
        int maxWin = (currentPlayer == 'X') ? 'X' * 3 : 'O' * 3;
        int minWin = (currentPlayer == 'X') ? 'O' * 3 : 'X' * 3;
        int currentSum = 0;

        //check rows
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                currentSum += gameBoard[i][j];
            }
            if (currentSum == maxWin) {
                return 10;
            }
            if (currentSum == minWin) {
                return -10;
            }
            currentSum = 0;
        }

        //check columns
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                currentSum += gameBoard[i][j];
            }
            if (currentSum == maxWin) {
                return 10;
            }
            if (currentSum == minWin) {
                return -10;
            }
            currentSum = 0;
        }

        //check diagonals
        if (gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2] == maxWin ||
                gameBoard[2][0] + gameBoard[1][1] + gameBoard[0][2] == maxWin) {
            return 10;
        }
        if (gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2] == minWin ||
                gameBoard[2][0] + gameBoard[1][1] + gameBoard[0][2] == minWin) {
            return -10;
        }

        return 0;
    }

    default boolean boardFull(char[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }
}

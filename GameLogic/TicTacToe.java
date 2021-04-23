package tictactoe.GameLogic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class TicTacToe {
    private char[][] gameBoard;
    private String currentState;
    private boolean running;
    private char currentTurn;
    private HashMap<String, AI> cpuPlayers;

    public TicTacToe() {
        this.currentState = "Game not finished";
        this.gameBoard = new char[3][3];
        for (char[] row : this.gameBoard) {
            Arrays.fill(row, '_');
        }
        this.currentTurn = 'X';
        this.running = true;
        cpuPlayers = new HashMap<>();
        cpuPlayers.put(new Easy().getName(), new Easy());
        cpuPlayers.put(new Medium().getName(), new Medium());
        cpuPlayers.put(new Hard().getName(), new Hard());
    }

    public Set<String> getDifficulties() {
        return cpuPlayers.keySet();
    }

    public void takeTurn(int[] turn) {
        this.gameBoard[turn[0]][turn[1]] = this.currentTurn;
        setCurrentState();
        this.currentTurn = this.currentTurn == 'X' ? 'O' : 'X';
    }

    public void cpuTurn(String difficulty) {
        takeTurn(cpuPlayers.get(difficulty).getTurn(this.gameBoard, this.currentTurn));
    }

    public String boardToString() {
        StringBuilder board = new StringBuilder();
        board.append("-".repeat(9));
        board.append("\n");
        for (char[] chars : this.gameBoard) {
            board.append("| ");
            for (int j = 0; j < this.gameBoard.length; j++) {
                board.append(chars[j]);
                board.append(" ");
            }
            board.append("|\n");
        }
        board.append("-".repeat(9));

        return board.toString();
    }

    private void setCurrentState() {

        switch (cpuPlayers.getOrDefault("easy", new Easy()).evaluateBoard(this.gameBoard, 'X')) {
            case 10:
                this.currentState = "X wins";
                this.running = false;
                break;
            case -10:
                this.currentState = "O wins";
                this.running = false;
                break;
            case 0:
                if (new Easy().boardFull(this.gameBoard)) {
                    this.currentState = "Draw";
                    this.running = false;
                    break;
                }
            default:
                this.currentState = "Game not finished";
                break;
        }
    }


    public String getCurrentState() {
        return this.currentState;
    }

    public boolean isOccupied(int i, int j) {
        return this.gameBoard[i][j] != '_';
    }

    public boolean isRunning() {
        return this.running;
    }
}


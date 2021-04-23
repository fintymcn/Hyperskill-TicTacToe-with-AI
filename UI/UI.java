package tictactoe.UI;

import tictactoe.GameLogic.TicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    private Scanner scanner;
    private TicTacToe game;
    private boolean setUp;
    private String playerOne;
    private String playerTwo;

    public UI(Scanner scanner, TicTacToe game) {
        this.scanner = scanner;
        this.game = game;
        this.setUp = false;
    }


    public void setUpGame() {
        String[] params = getParams();
        this.setUp = "start".equals(params[0]);
        playerOne = params[1];
        playerTwo = params[2];
    }

    public void runGame() {
        printBoard();
        while (this.game.isRunning()) {
            if ("user".equals(playerOne)) {
                userTurn();
            } else {
                cpuTurn(playerOne);
            }
            printBoard();
            if (!this.game.isRunning()) {
                break;
            }
            if ("user".equals(playerTwo)) {
                userTurn();
            } else {
                cpuTurn(playerTwo);
            }
            printBoard();
        }
        System.out.println(this.game.getCurrentState());
    }

    private void cpuTurn(String player) {
        System.out.printf("Making move level \"%s\"\n", player);
        this.game.cpuTurn(player);
    }


    private void userTurn() {
        int[] input = getCoordinates();
        this.game.takeTurn(input);
    }

    private void printBoard() {
        System.out.println(this.game.boardToString());
    }

    private int[] getCoordinates() {
        boolean acceptedInput = false;
        int[] coordinates = {-1, -1};
        while (!acceptedInput) {
            coordinates[0] = -1;
            coordinates[1] = -1;
            System.out.print("Enter the coordinates: ");
            String move = scanner.nextLine();

            try {
                coordinates[0] += Integer.parseInt(move.split(" ")[0]);
                coordinates[1] += Integer.parseInt(move.split(" ")[1]);
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (coordinates[0] < 0 || coordinates[0] > 2 || coordinates[1] < 0 || coordinates[1] > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (this.game.isOccupied(coordinates[0], coordinates[1])) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            acceptedInput = true;
        }
        return coordinates;
    }

    private String[] getParams() {
        boolean acceptedInput = false;
        String[] params = new String[3];
        List<String> players = new ArrayList<>(this.game.getDifficulties());
        players.add("user");


        while (!acceptedInput) {
            System.out.print("Input command: ");
            String input = scanner.nextLine();
            if ("exit".equals(input)) {
                return params;
            }
            if (input.split(" ").length != 3) {
                System.out.println("Bad parameters!");
                continue;
            }

            params = input.split(" ");

            if (!"start".equals(params[0]) || !players.contains(params[1]) || !players.contains(params[2])) {
                System.out.println("Bad parameters!");
                continue;
            }
            acceptedInput = true;
        }

        return params;
    }

    public boolean isSetUp() {
        return this.setUp;
    }
}

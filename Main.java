package tictactoe;

import tictactoe.GameLogic.TicTacToe;
import tictactoe.UI.UI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        UI ui = new UI(scanner, game);
        ui.setUpGame();
        if (ui.isSetUp()) {
            ui.runGame();
        }
    }
}

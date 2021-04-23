package tictactoe.GameLogic;

public class Easy implements AI {

    final String NAME = "easy";

    @Override
    public int[] getTurn(char[][] gameBoard, char currentPlayer) {
        return randomTurn(gameBoard);
    }

    @Override
    public String getName() {
        return NAME;
    }
}

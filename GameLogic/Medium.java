package tictactoe.GameLogic;

public class Medium implements AI {

    final String NAME = "medium";

    @Override
    public int[] getTurn(char[][] gameBoard, char currentPlayer) {
        int[] move = getWinBlockMove(gameBoard, currentPlayer);
        if (move[0] == -1) {
            return randomTurn(gameBoard);
        }
        return move;
    }

    private int[] getWinBlockMove(char[][] gameBoard, char currentPlayer) {
        int[] bestMove = new int[]{-1, -1};
        char opposingPlayer = currentPlayer == 'X' ? 'O' : 'X';
        //checks for winning move first
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == '_') {
                    gameBoard[i][j] = currentPlayer;
                    if (evaluateBoard(gameBoard, currentPlayer) == 10) {
                        gameBoard[i][j] = '_';
                        bestMove[0] = i;
                        bestMove[1] = j;
                        return bestMove;
                    }
                    gameBoard[i][j] = '_';
                }
            }
        }
        //then checks for blocking move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == '_') {
                    gameBoard[i][j] = opposingPlayer;
                    if (evaluateBoard(gameBoard, currentPlayer) == -10) {
                        gameBoard[i][j] = '_';
                        bestMove[0] = i;
                        bestMove[1] = j;
                        return bestMove;
                    }
                    gameBoard[i][j] = '_';
                }
            }
        }

        return bestMove;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

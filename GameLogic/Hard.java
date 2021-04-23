package tictactoe.GameLogic;

public class Hard implements AI {

    final String NAME = "hard";
    char player;
    char opponent;

    @Override
    public int[] getTurn(char[][] gameBoard, char currentPlayer) {
        this.player = currentPlayer;
        this.opponent = (this.player == 'X') ? 'O' : 'X';

        int bestValue = Integer.MIN_VALUE;
        int[] move = new int[]{-1, -1};

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] == '_') {
                    gameBoard[i][j] = currentPlayer;
                    int candidate = minimax(gameBoard, 0, false);
                    if (candidate > bestValue) {
                        bestValue = candidate;
                        move[0] = i;
                        move[1] = j;
                    }
                    gameBoard[i][j] = '_';
                }
            }
        }

        return move;
    }

    public int minimax(char[][] gameBoard, int depth, boolean isMaximising) {
        int score = evaluateBoard(gameBoard, player);
        if (score == 10 || score == -10 || boardFull(gameBoard)) {
            return score;
        }

        if (isMaximising) {
            int bestValue = Integer.MIN_VALUE;
            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard.length; j++) {
                    if (gameBoard[i][j] == '_') {
                        gameBoard[i][j] = this.player;
                        int value = minimax(gameBoard, depth + 1, false);
                        bestValue = Math.max(bestValue, value);
                        gameBoard[i][j] = '_';
                    }
                }
            }
            return bestValue - depth;
        } else {
            int bestValue = Integer.MAX_VALUE;
            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard.length; j++) {
                    if (gameBoard[i][j] == '_') {
                        gameBoard[i][j] = this.opponent;
                        int value = minimax(gameBoard, depth + 1, true);
                        bestValue = Math.min(bestValue, value);
                        gameBoard[i][j] = '_';
                    }
                }
            }
            return bestValue + depth;
        }
    }

    @Override
    public String getName() {
        return NAME;
    }



}

package edu.mco364;

enum Cell {NONE, O, X}

class TicTacToeModel {
    private boolean isDraw, isPlayerXMove;
    private Cell[][] grid;

    TicTacToeModel() {
        grid = new Cell[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                grid[row][col] = Cell.NONE;
            }
        }
    }

    Cell currentPlayer() {
        isPlayerXMove = !isPlayerXMove;
        return isPlayerXMove ? Cell.X : Cell.O;
    }

    void setCell(int row, int col, Cell cell) {
        if (grid[row][col] == Cell.NONE) {
            grid[row][col] = cell;
        }
    }

    boolean isGameOver() {
        int moveCounter = 0, row = 0, col = 0;

        if (grid[0][0] != Cell.NONE
                && grid[0][0] == grid[1][1]
                && grid[1][1] == grid[2][2]) {
            return true;
        }
        if (grid[0][2] != Cell.NONE
                && grid[0][2] == grid[1][1]
                && grid[1][1] == grid[2][0]) {
            return true;
        }

        for (row = 0; row < 3; row++) {
            for (col = 0; col < 3; col++) {
                if (grid[row][0] != Cell.NONE
                        && grid[row][0] == grid[row][1]
                        && grid[row][1] == grid[row][2] ||

                        grid[0][col] != Cell.NONE
                                && grid[0][col] == grid[1][col]
                                && grid[1][col] == grid[2][col]) {
                    return true;
                }

                if (grid[row][col] != Cell.NONE) {
                    moveCounter++;
                }
                if (moveCounter == 9) {
                    isDraw = true;
                    return true;
                }
            }
        }
        return false;
    }

    String getWinner(Cell currentPlayer) {
        if (isDraw) {
            return "Draw.";
        }
        return "Player " + currentPlayer + " wins!";
    }
}
package org.example;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class BacktrackSudokuSolverTest {

    private boolean isSafe(int[][] board, int row, int col, int num) {
        for (int d = 0; d < board.length; d++) {
            if (board[row][d] == num) {
                return false;
            }
        }

        for (int r = 0; r < board.length; r++) {

            if (board[r][col] == num) {
                return false;
            }
        }

        int sqrt = (int) Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart; d < boxColStart + sqrt; d++) {
                if (board[r][d] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean testBoard(int[][] board) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int buf = board[i][j];
                board[i][j] = 0;
                if (!isSafe(board, i, j, buf)) {
                    System.out.println("Bad i: %d and %d board: %d".formatted(i, j, board[i][j]));
                    return false;
                }
                board[i][j] = buf;
            }

        }
        return true;
    }


    @Test
    void testSolveGeneratesDiffrentBoard() {
        BacktrackSudokuSolver solver = new BacktrackSudokuSolver();

        int[][] board = new int[9][9];
        for (int[] array : board) {
            Arrays.fill(array, 0);
        }
        solver.solve(board);

        int[][] board2 = new int[9][9];
        for (int[] array : board2) {
            Arrays.fill(array, 0);
        }
        solver.solve(board2);
    }

    @Test
    void checkCorrectness() {
        int[][] board = new int[9][9];
        for (int[] array : board) {
            Arrays.fill(array, 0);
        }
        BacktrackSudokuSolver solver = new BacktrackSudokuSolver();
        solver.solve(board);
        testBoard(board);

    }

}


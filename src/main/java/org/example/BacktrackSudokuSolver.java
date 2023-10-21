package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BacktrackSudokuSolver implements SudokuSolver {

    private final int boardWidth = 9;
    private int[] sequence = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    private void shuffleSequence() {
        List<Integer> sequenceList = Arrays.stream(sequence).boxed().collect(Collectors.toList());
        Collections.shuffle(sequenceList, new Random());

        for (int i = 0; i < 9; i++) {
            sequence[i] = sequenceList.get(i);
        }
    }

    @Override
    public void solve(int[][] board) {
        shuffleSequence();
        solveSudoku(board);

    }

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

    private boolean solveSudoku(int[][] board) {
        int row = 0;
        int col = 0;
        boolean isEmpty = true;

        // where should algorithm start
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;

                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // No empty space left
        if (isEmpty) {
            return true;
        }

        for (int num : sequence) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board)) {
                    return true;
                } else {
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

}

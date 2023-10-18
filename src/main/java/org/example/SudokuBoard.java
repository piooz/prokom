package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SudokuBoard {
    private int[][] board = new int[9][9];
    private int[] sequence = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    public SudokuBoard() {
        shuffle();
    }

    public int[][] getBoard() {
        int[][] copy = Arrays.copyOf(board, 9);
        return copy;
    }

    public void fillBoard() {
        for (int[] var : board) {
            Arrays.fill(var, 0);
        }

        shuffle();
        solveSudoku(9);
    }

    public boolean solveSudoku(int n) {
        int row = 0;
        int col = 0;
        boolean isEmpty = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
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

        // Else for each-row backtrack
        // for (int num = 1; num <= n; num++) {
        for (int num : sequence) {
            if (isSafe(row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(n)) {
                    // print(board, n);
                    return true;
                } else {
                    // replace it
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "SudokuBoard [board=" + Arrays.toString(board) + ", sequence=" + Arrays.toString(sequence) + "]";
    }

    public String getBoardString() {
        String out = new String("");

        for (int[] var : board) {
            System.out.println(var);
            for (int num : var) {
                out += String.valueOf(num);
                out += ", ";
            }
            out += "\n";
        }
        return out;
    }

    private void shuffle() {

        List<Integer> sequenceList = Arrays.stream(sequence).boxed().collect(Collectors.toList());
        Collections.shuffle(sequenceList, new Random());

        for (int i = 0; i < 9; i++) {
            sequence[i] = sequenceList.get(i);
        }
    }

    private boolean isSafe(int row, int col, int num) {
        // Row has the unique (row-clash)
        for (int d = 0; d < board.length; d++) {

            if (board[row][d] == num) {
                return false;
            }
        }

        // Column has the unique numbers (column-clash)
        for (int r = 0; r < board.length; r++) {

            if (board[r][col] == num) {
                return false;
            }
        }

        // Corresponding square has
        // unique number (box-clash)
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
        // if there is no clash, it's safe
        return true;
    }

}

package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SudokuBoard {
    private final int boardWidth = 9;
    private int[][] board = new int[boardWidth][boardWidth];
    private int[] sequence = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    public int getCell(int row, int col) {
        return board[row][col];
    }

    public int[][] getBoard() {
        int[][] copy = new int [boardWidth][boardWidth];
        for (int i = 0; i < boardWidth; i++) {
            copy[i] = board[i].clone();
        }

        return copy;
    }

    public void fillBoard() {
        for (int[] var : board) {
            Arrays.fill(var, 0);
        }

        shuffle_sequence();
        solveSudoku();
    }

    public boolean solveSudoku() {
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
            if (isSafe(row, col, num)) {
                board[row][col] = num;
                if (solveSudoku()) {
                    return true;
                } else {
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
            for (int num : var) {
                out += String.valueOf(num);
                out += " ";
            }
            out += "\n";
        }
        return out;
    }

    private void shuffle_sequence() {
        List<Integer> sequenceList = Arrays.stream(sequence).boxed().collect(Collectors.toList());
        Collections.shuffle(sequenceList, new Random());

        for (int i = 0; i < 9; i++) {
            sequence[i] = sequenceList.get(i);
        }
    }

    private boolean isSafe(int row, int col, int num) {
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

}

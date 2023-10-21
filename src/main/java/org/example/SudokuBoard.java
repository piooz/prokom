package org.example;

import java.util.Arrays;

public class SudokuBoard {
    private SudokuSolver sudokuSolver;
    private final int boardWidth = 9;
    private int[][] board = new int[boardWidth][boardWidth];

    public SudokuBoard(SudokuSolver sudokuSolver) {
        for (int[] var : board) {
            Arrays.fill(var, 0);
        }
        this.sudokuSolver = sudokuSolver;
    }

    public int get(int row, int col) {
        return board[row][col];
    }

    public void set(int row, int col, int num) {
        board[row][col] = num;
    }

    public int[][] getBoard() {
        int[][] copy = new int[boardWidth][boardWidth];
        for (int i = 0; i < boardWidth; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }

    public void solveGame() {
        sudokuSolver.solve(board);
    }

    @Override
    public String toString() {
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

}

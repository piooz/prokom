package jaka.jakakolwiek;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(board);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SudokuBoard other = (SudokuBoard) obj;
        if (!Arrays.deepEquals(board, other.board)) {
            return false;
        }
        return true;
    }

    public void solveGame() {
        sudokuSolver.solve(this);
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

package jaka.jakakolwiek;

import jaka.jakakolwiek.types.SudokuField;

public class SudokuBoard {
    private SudokuSolver sudokuSolver;
    private SudokuField[][] board;

    public SudokuBoard(SudokuSolver sudokuSolver, SudokuField[][] board) {
        this.sudokuSolver = sudokuSolver;
        this.board = board;
    }

    public int get(int row, int col) {
        return board[row][col].getFieldValue();
    }

    public void set(int row, int col, int num) {
        board[row][col].setFieldValue(num);
    }

    public SudokuRow getRow(int y) {
        return new SudokuRow(board[y]);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] array = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            array[i] = board[i][x];
        }
        return new SudokuColumn(array);
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] array = new SudokuField[9];

        int sectionX = (x / 3) * 3;
        int sectionY = (y / 3) * 3;
        int index = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[index] = board[sectionY + j][sectionX + i];
                index++;
            }
        }
        return new SudokuBox(array);
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    // private boolean checkBoard() {
    // for (int i = 0; i < 9; i++) {
    // if (!(getRow(i).verify() && getColumn(i).verify())) {
    // return false;
    // }
    //
    // if (!(i % 3 == 0 && getBox(i, i).verify())) {
    // return false;
    // }
    // }
    // return true;
    // }
    //
    // @Override
    // public void update(Integer event) {
    // checkBoard();
    // }

}

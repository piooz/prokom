package jaka.jakakolwiek;

import jaka.jakakolwiek.types.SudokuField;

import java.util.*;

public class SudokuBoard {
    private SudokuSolver sudokuSolver;
    private List<SudokuField> board;

    public SudokuBoard(SudokuSolver sudokuSolver) {
        this.sudokuSolver = sudokuSolver;
        SudokuField[] array = new SudokuField[81];
        for (int i = 0; i < 81; i++) {
            array[i] = new SudokuField();
        }
        board = Arrays.asList(array);
    }

    public int get(int row, int col) {
        int index = row * 9 + col;
        return board.get(index).getFieldValue();
    }

    public void set(int row, int col, int num) {
        int index = row * 9 + col;
        board.get(index).setFieldValue(num);
    }

    public SudokuRow getRow(int y) {
        SudokuField[] array = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            array[i] = board.get(9 * y + i);
        }
        return new SudokuRow(array);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] array = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            array[i] = board.get(9 * i + x);
        }
        return new SudokuColumn(array);
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] array = new SudokuField[9];

        int sectionX = (x / 3) * 3;
        int sectionY = (y / 3) * 3;
        int index = 0;

        for (int i = sectionY; i < sectionY + 3; i++) {
            for (int j = sectionX; j < sectionX + 3; j++) {
                array[index++] = board.get(i * 9 + j);
            }
        }
        return new SudokuBox(array);
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }
}

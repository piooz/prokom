package jaka.jakakolwiek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import jaka.jakakolwiek.types.SudokuField;

@ExtendWith(MockitoExtension.class)
public class SudokuBoardTest {

    @Mock
    SudokuSolver solver;

    SudokuField[][] board;

    @BeforeEach
    void beforeEach() {
        board = new SudokuField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField();
            }
        }
    }

    @AfterEach
    void afterEach() {
        board = null;
    }

    @Test
    void isSudokuUsingSolver() {
        Mockito.doNothing().when(solver).solve(Mockito.any(SudokuBoard.class));
        SudokuBoard sudokuBoard = new SudokuBoard(solver, board);
        sudokuBoard.solveGame();
        Mockito.verify(solver, Mockito.times(1)).solve(Mockito.any(SudokuBoard.class));
    }

    @Test
    void getTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(solver, board);
        int sudokuField = sudokuBoard.get(0, 0);

        assertEquals(0, sudokuField);
    }

    @Test
    void setTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(solver, board);

        int number = 3;
        sudokuBoard.set(0, 0, number);
        Assertions.assertEquals(number, sudokuBoard.get(0, 0));
    }

    void setupboard(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, j + 1);
            }
        }
    }

    @Test
    void testGetBox() {
        SudokuBoard sudokuBoard = new SudokuBoard(solver, board);
        setupboard(sudokuBoard);

        SudokuBox box = sudokuBoard.getBox(7, 1);
        assertNotEquals(box, null);
    }

    @Test
    void testGetColumn() {
        SudokuBoard sudokuBoard = new SudokuBoard(solver, board);
        setupboard(sudokuBoard);

        SudokuColumn col = sudokuBoard.getColumn(5);
        assertNotEquals(col, null);

    }

    @Test
    void testGetRow() {
        SudokuBoard sudokuBoard = new SudokuBoard(solver, board);
        setupboard(sudokuBoard);

        SudokuRow row = sudokuBoard.getRow(5);
        assertNotEquals(row, null);
    }
}

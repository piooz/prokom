package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SudokuBoardTest {
    @Test
    void testSolveSudoku() {

    }

    @Test
    void testFillboard() {

    }

    @Test
    void ConstructorDoesNotThrow() {
        Assertions.assertDoesNotThrow(() -> {
            SudokuBoard board = new SudokuBoard();
        });
    }

    @Test
    void solveSudokuDoesNotThrow() {
        Assertions.assertDoesNotThrow(() -> {
            SudokuBoard board = new SudokuBoard();
            board.solveSudoku(9);
        });
    }

    @Test
    void fillBoardTest() {
        Assertions.assertDoesNotThrow(() -> {
            SudokuBoard board = new SudokuBoard();
            board.solveSudoku(9);
        });
    }

}

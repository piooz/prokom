package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class SudokuBoardTest {

    private SudokuBoard sudokuBoard;

    @BeforeEach
    void beforeEach() {
        sudokuBoard = new SudokuBoard();
    }

    @AfterEach
    void AfterEach() {
        sudokuBoard = null;
    }

    @Test
    void solveSudokuDoesNotThrow() {
        Assertions.assertDoesNotThrow(() -> {
            sudokuBoard.fillBoard();
        });
    }

    @Test
    void getBoardTest() {
        int[][] board = sudokuBoard.getBoard();
        board[0][0] = 123;

        int[][] board2 = sudokuBoard.getBoard();
        board2[0][0] = -321;

        assertNotEquals(board[0][0], board2[0][0]);
    }

    @Test
    void solveSudokuDoesntContainZero() {
        Assertions.assertDoesNotThrow(() -> {
            sudokuBoard.fillBoard();
        });

        for (int[] var : sudokuBoard.getBoard()) {
            for (int num : var) {
                assertNotEquals(0, num);
            }
        }
    }

    @Test
    void genereateUniqueBoard() {
        sudokuBoard.fillBoard();
        String out1 = sudokuBoard.getBoardString();
        System.out.println(out1);

        sudokuBoard.fillBoard();
        String out2 = sudokuBoard.getBoardString();
        System.out.println(out2);

        Assertions.assertNotEquals(out1, out2);
    }

    @Test
    void testGetCell() {
        int cell = sudokuBoard.getCell(0, 0);
        cell = 1234;

        int cell2 = sudokuBoard.getCell(0, 0);

        assertNotEquals(cell, cell2);

    }

}

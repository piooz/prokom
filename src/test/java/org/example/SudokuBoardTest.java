package org.example;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SudokuBoardTest {

    private SudokuBoard sudokuBoard;

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

    private boolean testBoard(int[][] board) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int buf = board[i][j];
                board[i][j] = 0;
                if (!isSafe(board, i, j, buf)) {
                    System.out.println("Bad i: %d and %d board: %d".formatted(i, j, board[i][j]));
                    return false;
                }
                board[i][j] = buf;
            }

        }
        return true;
    }

    @BeforeEach
    void beforeEach() {
        sudokuBoard = new SudokuBoard();
    }

    @AfterEach
    void afterEach() {
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
    void checkCorrectness() {
        sudokuBoard.fillBoard();
        System.out.println(sudokuBoard.getBoardString());
        assertTrue(testBoard(sudokuBoard.getBoard()));
    }

    @Test
    void genereateUniqueBoard() {
        sudokuBoard.fillBoard();
        int[][] out1 = sudokuBoard.getBoard();
        System.out.println(out1);

        sudokuBoard.fillBoard();
        int[][] out2 = sudokuBoard.getBoard();
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

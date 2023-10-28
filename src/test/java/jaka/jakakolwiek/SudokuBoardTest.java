package jaka.jakakolwiek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SudokuBoardTest {

    private class MockSolver implements SudokuSolver {
        private int uses = 0;

        public int getUses() {
            return uses;
        }

        @Override
        public void solve(SudokuBoard board) {
            uses++;
        }
    }

    @Test
    void getBoardTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(new MockSolver());
        int[][] board = sudokuBoard.getBoard();
        board[0][0] = 123;

        int[][] board2 = sudokuBoard.getBoard();
        board2[0][0] = -321;

        assertNotEquals(board[0][0], board2[0][0]);
    }

    @Test
    void solveSudokuDoesntContainZero() {
        SudokuBoard sudokuBoard = new SudokuBoard(new MockSolver());
        for (int[] var : sudokuBoard.getBoard()) {
            for (int num : var) {
                assertEquals(0, num);
            }
        }
    }

    @Test
    void isSudokuUsingSolver() {
        MockSolver mock = new MockSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(mock);
        sudokuBoard.solveGame();
        assertTrue(mock.getUses() > 0);
    }

    @Test
    void genereateUniqueBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard(new MockSolver());
        sudokuBoard.solveGame();
        int[][] out1 = sudokuBoard.getBoard();
        System.out.println(out1);

        sudokuBoard.solveGame();
        int[][] out2 = sudokuBoard.getBoard();
        System.out.println(out2);

        Assertions.assertNotEquals(out1, out2);
    }

    @Test
    void getTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(new MockSolver());
        int cell = sudokuBoard.get(0, 0);
        cell = 1234;

        int cell2 = sudokuBoard.get(0, 0);

        assertNotEquals(cell, cell2);
    }

    @Test
    void setTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(new MockSolver());
        int number = 3;
        sudokuBoard.set(0, 0, number);
        Assertions.assertEquals(number, sudokuBoard.get(0, 0));
    }

    @Test
    void toStringTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(new MockSolver());
        Assertions.assertNotNull(sudokuBoard.toString());
    }

    @Test
    void testEquals() {
        SudokuSolver solver = new MockSolver();
        SudokuBoard board1 = new SudokuBoard(solver);
        SudokuBoard board2 = new SudokuBoard(solver);
        board2.set(1,1,3);
        assertFalse(board1 == board2);
    }

    @Test
    void testEqualsObj() {
        SudokuSolver solver = new MockSolver();
        SudokuBoard board1 = new SudokuBoard(solver);
        Object board2 = new Object();
        assertFalse(board1 == board2);
    }

    @Test
    void testEqualsNull() {
        SudokuSolver solver = new MockSolver();
        SudokuBoard board1 = new SudokuBoard(solver);
        SudokuBoard board2 = null;
        assertFalse(board1 == board2);
    }

    @Test
    void testEqualsSameReferce() {
        SudokuSolver solver = new MockSolver();
        SudokuBoard board1 = new SudokuBoard(solver);
        SudokuBoard board2 = board1;
        assertTrue(board1 == board2);
    }

    @Test
    void testHashCode() {
        SudokuSolver solver = new MockSolver();
        SudokuBoard board1 = new SudokuBoard(solver);
        SudokuBoard board2 = new SudokuBoard(solver);
        board2.set(1,1,3);
        assertNotEquals(board2.hashCode(), board1.hashCode());

    }

}

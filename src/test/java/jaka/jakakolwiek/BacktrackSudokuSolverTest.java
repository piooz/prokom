package jaka.jakakolwiek;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jaka.jakakolwiek.types.SudokuField;

public class BacktrackSudokuSolverTest {

    private boolean isSafe(SudokuBoard board, int row, int col, int num) {
        int boardWidth = 9;
        for (int d = 0; d < boardWidth; d++) {
            if (board.get(row, d) == num) {
                return false;
            }
        }

        for (int r = 0; r < boardWidth; r++) {

            if (board.get(r, col) == num) {
                return false;
            }
        }

        int sqrt = (int) Math.sqrt(boardWidth);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart; d < boxColStart + sqrt; d++) {
                if (board.get(r, d) == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean testBoard(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                int buf = board.get(i, j);
                board.set(i, j, 0);

                if (!isSafe(board, i, j, buf)) {
                    return false;
                }
                board.set(i, j, buf);
            }
        }
        return true;
    }

    @Test
    void testSolveGeneratesDiffrentBoard() {
        SudokuField[][] board1 = new SudokuField[9][9];
        SudokuField[][] board2 = new SudokuField[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board1[i][j] = new SudokuField();
                board2[i][j] = new SudokuField();
            }
        }

        BacktrackSudokuSolver solver = new BacktrackSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver, board1);
        SudokuBoard sudokuBoard2 = new SudokuBoard(solver, board2);

        sudokuBoard.solveGame();
        sudokuBoard2.solveGame();

        assertNotEquals(sudokuBoard, sudokuBoard2);
    }

    @Test
    void checkCorrectness() {
        SudokuField[][] board1 = new SudokuField[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board1[i][j] = new SudokuField();
            }
        }

        SudokuBoard board = new SudokuBoard(new BacktrackSudokuSolver(), board1);
        board.solveGame();
        assertTrue(testBoard(board));
    }
}

package jaka.jakakolwiek;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
                    System.out.println("Bad i: %d and %d board: %d".formatted(i, j, board));
                    return false;
                }
                board.set(i, j, buf);
            }
        }
        return true;
    }

    @Test
    void testSolveGeneratesDiffrentBoard() {
        BacktrackSudokuSolver solver = new BacktrackSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuBoard board2 = new SudokuBoard(solver);

        board.solveGame();
        board2.solveGame();

        assertNotEquals(board, board2);
    }

    @Test
    void checkCorrectness() {
        SudokuBoard board = new SudokuBoard(new BacktrackSudokuSolver());
        board.solveGame();
        assertTrue(testBoard(board));
    }

}

package jaka.jakakolwiek;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BacktrackSudokuSolver implements SudokuSolver {

    private final int boardWidth = 9;
    private int[] sequence = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    private void shuffleSequence() {
        List<Integer> sequenceList = Arrays.stream(sequence).boxed().collect(Collectors.toList());
        Collections.shuffle(sequenceList, new Random());

        for (int i = 0; i < 9; i++) {
            sequence[i] = sequenceList.get(i);
        }
    }

    @Override
    public void solve(SudokuBoard board) {
        shuffleSequence();
        solveSudoku(board);
    }

    private boolean isSafe(SudokuBoard board, int row, int col, int num) {
        board.set(row, col, num);

        boolean isBoxValid = board.getBox(col, row).verify();
        boolean isColumnValid = board.getColumn(col).verify();
        boolean isRowValid = board.getRow(row).verify();

        if (isBoxValid && isColumnValid && isRowValid) {
            board.set(row, col, 0);
            return true;
        } else {
            board.set(row, col, 0);
            return false;
        }
    }

    private boolean solveSudoku(SudokuBoard board) {
        int row = 0;
        int col = 0;
        boolean isEmpty = true;

        // where should algorithm start
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (board.get(i, j) == 0) {
                    row = i;
                    col = j;

                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // No empty space left
        if (isEmpty) {
            return true;
        }

        for (int num : sequence) {
            if (isSafe(board, row, col, num)) {
                board.set(row, col, num);
                if (solveSudoku(board)) {
                    return true;
                } else {
                    board.set(row, col, 0);
                }
            }
        }
        return false;
    }

}

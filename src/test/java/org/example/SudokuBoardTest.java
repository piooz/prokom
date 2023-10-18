package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SudokuBoardTest {

    @Test
    void ConstructorDoesNotThrow() {
        Assertions.assertDoesNotThrow(() -> {
            new SudokuBoard();
        });
    }

    @Test
    void solveSudokuDoesNotThrow() {
        Assertions.assertDoesNotThrow(() -> {
            SudokuBoard board = new SudokuBoard();
            board.fillBoard();
        });
    }

    @Test
    void solveSudokuDoesntContainZero() {
        SudokuBoard board = new SudokuBoard();
        Assertions.assertDoesNotThrow(() -> {
            board.fillBoard();
        });

        for (int[] var : board.getBoard()) {
            for (int num : var) {
                System.out.print(String.format("%d ", num));
            }
            System.out.print('\n');
        }
    }

    @Test
    void genereateUniqueBoard() {
        SudokuBoard board = new SudokuBoard();
        board.fillBoard();
        String out1 = board.getBoardString();
        System.out.println(out1);

        board.fillBoard();
        String out2 = board.getBoardString();
        System.out.println(out2);

        Assertions.assertNotEquals(out1, out2);
    }

}

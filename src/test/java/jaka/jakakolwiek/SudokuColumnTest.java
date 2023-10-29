package jaka.jakakolwiek;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jaka.jakakolwiek.types.SudokuField;

public class SudokuColumnTest {

    @Test
    void testTrueVerify() {
        SudokuField[] array = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            array[i] = new SudokuField(i);
        }
        SudokuColumn section = new SudokuColumn(array);

        assertTrue(section.verify());
    }

    @Test
    void testFalseVerify() {
        SudokuField[] array = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            array[i] = new SudokuField(1);
        }

        SudokuColumn section = new SudokuColumn(array);

        assertFalse(section.verify());
    }
}

package jaka.jakakolwiek.types;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SudokuFieldTest {

    @Test
    void testGetValue() {
        SudokuField field = new SudokuField();
        assertEquals(0, field.getFieldValue());
    }

    @Test
    void testSetValue() {
        SudokuField field = new SudokuField();

        assertDoesNotThrow(() -> {
            field.setFieldValue(1);
        });
    }

    @Test
    void testSetValuebetween() {
        SudokuField field = new SudokuField();
        assertThrows(IllegalArgumentException.class, () -> {
            field.setFieldValue(10);
        });
    }

    @Test
    void testSetIllegalValue() {
        SudokuField field = new SudokuField();

        assertThrows(IllegalArgumentException.class, () -> {
            field.setFieldValue(-2);
        });
    }
}
